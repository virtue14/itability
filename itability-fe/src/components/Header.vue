<template>
  <header class="header">
    <div class="header-content">
      <a class="logo-link" @click.prevent="changeRouter('/')">
        <img src="@/assets/logo.png" alt="itAbility" class="logo" />
      </a>

      <nav class="nav-links">
        <a class="nav-link" @click.prevent="changeRouter('/')">피드</a>
        <a class="nav-link" @click.prevent="changeRouter('/recruit')">모집글</a>
        <a class="nav-link" @click.prevent="changeRouter('/jobs')">채용정보</a>
        <a class="nav-link" v-if="isLoggedIn" @click.prevent="changeRouter('/mypage')">마이페이지</a>
      </nav>

      <div class="header-right">
        <div class="weather-info" v-if="weatherInfo.icon">
          <img :src="'https://openweathermap.org/img/wn/' + weatherInfo.icon + '.png'" alt="날씨" class="weather-icon" />
          <span class="temperature">{{ weatherInfo.temperature }}°C</span>
        </div>
        <a v-if="!isLoggedIn" class="auth-link" @click.prevent="changeRouter('/login')">로그인</a>
        <a v-else class="auth-link" @click.prevent="logout">로그아웃</a>
      </div>
    </div>
  </header>
</template>

<style scoped>
.header {
  border-bottom: 1px solid #eee;
  padding: 0 40px;
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #fff;
}

@media (max-width: 991px) {
  .header {
    padding: 0 16px;
  }
}

.header-content {
  display: flex;
  align-items: center;
  max-width: 1200px;
  width: 100%;
  gap: 32px;
}

.logo-link {
  cursor: pointer;
  display: flex;
  align-items: center;
}

.logo {
  height: 44px;
  object-fit: contain;
}

.nav-links {
  display: flex;
  align-items: center;
  gap: 24px;
}

.nav-link {
  font-family: Inter, sans-serif;
  font-size: 14px;
  font-weight: 500;
  color: #666;
  cursor: pointer;
  text-decoration: none;
  transition: color 0.2s;
}

.nav-link:hover {
  color: #333;
}

.header-right {
  display: flex;
  align-items: center;
  gap: 16px;
  margin-left: auto;
}

.weather-info {
  display: flex;
  align-items: center;
  gap: 2px;
}

.weather-icon {
  width: 28px;
  height: 28px;
  object-fit: contain;
}

.temperature {
  font-family: Inter, sans-serif;
  font-size: 13px;
  color: #666;
}

.auth-link {
  font-family: Inter, sans-serif;
  font-size: 13px;
  font-weight: 600;
  color: #fff;
  background: linear-gradient(96deg, #c9e1f2 35%, #dac9f2 100%);
  border-radius: 6px;
  padding: 6px 14px;
  cursor: pointer;
  text-decoration: none;
  white-space: nowrap;
}

.auth-link:hover {
  opacity: 0.9;
}
</style>

<script setup>
import { useRouter } from 'vue-router';
import { ref, onMounted } from 'vue';

const router = useRouter();
const isLoggedIn = ref(!!localStorage.getItem('token'));

const changeRouter = (path) => {
  router.push(path);
};

const logout = () => {
  localStorage.removeItem('token');
  isLoggedIn.value = false;
  router.push('/');
};

const weatherInfo = ref({
  temperature: null,
  icon: null
});

const fetchWeather = async () => {
  const apiKey = import.meta.env.VITE_OPENWEATHER_API_KEY;
  if (!apiKey) return;

  try {
    const response = await fetch(
      `https://api.openweathermap.org/data/2.5/weather?q=Seoul&appid=${apiKey}&units=metric`
    );
    if (!response.ok) return;
    const data = await response.json();
    weatherInfo.value = {
      temperature: Math.round(data.main.temp),
      icon: data.weather[0].icon
    };
  } catch {
    // 날씨 로드 실패 시 숨김
  }
};

onMounted(fetchWeather);
</script>
