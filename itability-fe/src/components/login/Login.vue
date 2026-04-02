<template>
  <div class="main-container">
    <main class="main-content">
      <img src="@/assets/logo.png" alt="itAbility" class="main-logo" />
      <p class="main-description">
        소통하며 지식을 나누고 협업할 수 있는<br/>
        IT 개발자 커뮤니티, 무한한 가능성의 세계로 초대합니다!
      </p>

      <div class="login-form">
        <div class="input-group">
          <input type="email" v-model="email" placeholder="이메일을 입력해 주세요" class="login-input" />
        </div>
        <div class="input-group">
          <input type="password" v-model="password" placeholder="비밀번호를 입력해 주세요" class="login-input" />
        </div>
        <button class="login-btn" @click="onLogin">로그인하기</button>
      </div>

      <div class="divider">
        <span class="divider-line"></span>
        <span class="divider-text">SNS 계정으로 간편하게 시작하기</span>
        <span class="divider-line"></span>
      </div>

      <div class="social-icons">
        <a :href="`${apiBaseUrl}${oauth2GoogleUrl}`" class="social-icon-link">
          <img src="@/assets/google_login.png" alt="구글 로그인" class="social-login-btn" />
        </a>
        <a :href="`${apiBaseUrl}${oauth2KakaoUrl}`" class="social-icon-link">
          <img src="@/assets/kakao_login.png" alt="카카오 로그인" class="social-login-btn" />
        </a>
        <a :href="`${apiBaseUrl}${oauth2GithubUrl}`" class="social-icon-link">
          <img src="@/assets/github_login.png" alt="깃허브 로그인" class="social-login-btn" />
        </a>
        <a :href="`${apiBaseUrl}${oauth2NaverUrl}`" class="social-icon-link">
          <img src="@/assets/naver_login.png" alt="네이버 로그인" class="social-login-btn" />
        </a>
      </div>
    </main>
  </div>
</template>

<style scoped>
.main-container {
  background-color: #fff;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  flex: 1;
}

.main-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  max-width: 400px;
  padding: 0 20px;
  box-sizing: border-box;
}

.main-logo {
  width: 180px;
  object-fit: contain;
}

.main-description {
  color: #888;
  text-align: center;
  margin-top: 6px;
  margin-bottom: 0;
  font: 400 14px/1.6 Inter, sans-serif;
}

.login-form {
  width: 100%;
  margin-top: 36px;
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.input-group {
  width: 100%;
}

.login-input {
  width: 100%;
  padding: 14px 16px;
  border: 1px solid #ddd;
  border-radius: 8px;
  font-size: 14px;
  font-family: Inter, sans-serif;
  box-sizing: border-box;
  outline: none;
  transition: border-color 0.2s;
}

.login-input:focus {
  border-color: #c9e1f2;
}

.login-input::placeholder {
  color: #aaa;
}

.login-btn {
  width: 100%;
  padding: 14px;
  background: linear-gradient(96deg, #c9e1f2 35%, #dac9f2 100%);
  color: #fff;
  font-family: Inter, sans-serif;
  font-size: 16px;
  font-weight: 700;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  margin-top: 4px;
}

.login-btn:hover {
  opacity: 0.9;
}

.divider {
  display: flex;
  align-items: center;
  width: 100%;
  margin-top: 32px;
  margin-bottom: 20px;
  gap: 12px;
}

.divider-line {
  flex: 1;
  height: 1px;
  background-color: #ddd;
}

.divider-text {
  font-family: Inter, sans-serif;
  font-size: 13px;
  color: #999;
  white-space: nowrap;
}

.social-icons {
  display: flex;
  gap: 20px;
  justify-content: center;
  align-items: center;
  margin-bottom: 48px;
}

.social-icon-link {
  cursor: pointer;
}

.social-login-btn {
  height: 44px;
  object-fit: contain;
}
</style>

<script setup>
import { ref } from 'vue';

const apiBaseUrl = import.meta.env.VITE_API_BASE_URL;
const oauth2KakaoUrl = import.meta.env.VITE_OAUTH2_KAKAO_URL;
const oauth2NaverUrl = import.meta.env.VITE_OAUTH2_NAVER_URL;
const oauth2GoogleUrl = import.meta.env.VITE_OAUTH2_GOOGLE_URL;
const oauth2GithubUrl = import.meta.env.VITE_OAUTH2_GITHUB_URL;

const email = ref('');
const password = ref('');

const onLogin = () => {
  // TODO: 이메일/비밀번호 로그인 구현
};
</script>
