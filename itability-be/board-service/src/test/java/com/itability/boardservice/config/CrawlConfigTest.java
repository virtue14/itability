package com.itability.boardservice.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link CrawlConfig}.
 *
 * <p>These tests validate the Spring configuration class structure without
 * starting a full application context or requiring an actual ChromeDriver
 * binary, keeping the tests fast and environment-independent.
 */
class CrawlConfigTest {

    // ── Class-level annotations ──────────────────────────────────────────────

    @Test
    void classIsAnnotatedWithConfiguration() {
        assertThat(CrawlConfig.class).hasAnnotation(Configuration.class);
    }

    /**
     * Confirms that the unused {@code @EntityScan} import removed in this PR
     * did not leave any stray class-level annotation behind.
     */
    @Test
    void classDoesNotHaveEntityScanAnnotation() throws ClassNotFoundException {
        // @EntityScan was removed as an unused import; confirm no annotation remnant
        Class<?> entityScanClass = null;
        try {
            entityScanClass = Class.forName(
                    "org.springframework.boot.autoconfigure.domain.EntityScan");
        } catch (ClassNotFoundException e) {
            // Not on classpath at all – nothing to check
            return;
        }

        assertThat(CrawlConfig.class.isAnnotationPresent(
                (Class<? extends java.lang.annotation.Annotation>) entityScanClass))
                .isFalse();
    }

    // ── Field-level annotations ──────────────────────────────────────────────

    @Test
    void chromeDriverPathFieldExistsAndHasValueAnnotation() throws NoSuchFieldException {
        Field field = CrawlConfig.class.getDeclaredField("chromeDriverPath");

        assertThat(field).isNotNull();
        assertThat(field.isAnnotationPresent(Value.class)).isTrue();
    }

    @Test
    void chromeDriverPathValueAnnotationReferencesCorrectProperty() throws NoSuchFieldException {
        Field field = CrawlConfig.class.getDeclaredField("chromeDriverPath");
        Value valueAnnotation = field.getAnnotation(Value.class);

        assertThat(valueAnnotation.value()).isEqualTo("${webdriver.chrome.driver}");
    }

    // ── Method-level annotations ─────────────────────────────────────────────

    @Test
    void webDriverMethodExistsAndHasBeanAnnotation() throws NoSuchMethodException {
        Method method = CrawlConfig.class.getDeclaredMethod("webDriver");

        assertThat(method).isNotNull();
        assertThat(method.isAnnotationPresent(Bean.class)).isTrue();
    }

    @Test
    void webDriverMethodReturnsWebDriverType() throws NoSuchMethodException {
        Method method = CrawlConfig.class.getDeclaredMethod("webDriver");

        assertThat(method.getReturnType().getName())
                .isEqualTo("org.openqa.selenium.WebDriver");
    }

    // ── Instantiation ────────────────────────────────────────────────────────

    @Test
    void crawlConfigCanBeInstantiated() {
        // The class must be instantiable so Spring can create it as a bean.
        // This also confirms no static initialiser block throws.
        assertThat(new CrawlConfig()).isNotNull();
    }
}