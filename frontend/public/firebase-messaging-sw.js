// Give the service worker access to Firebase Messaging.
// Note that you can only use Firebase Messaging here, other Firebase libraries
// are not available in the service worker.
importScripts("https://www.gstatic.com/firebasejs/8.9.0/firebase-app.js");
importScripts("https://www.gstatic.com/firebasejs/8.9.0/firebase-messaging.js");

if (firebase.messaging().isSupported()) {
  // Initialize the Firebase app in the service worker by passing in the
  // messagingSenderId.
  firebase.initializeApp({
    apiKey: "AIzaSyAjoO1hK9KSuV3oEsXBjxf0BdSoFwJO6HY",
    projectId: "ohnaco-notification",
    messagingSenderId: "1097451483774",
    appId: "1:1097451483774:web:03068b7284ab4b03ea5e2f",
  });

  // Retrieve an instance of Firebase Messaging so that it can handle background
  // messages.
  const messaging = firebase.messaging();

  // 백그라운드 상태에서 받은 알림 처리
  messaging.setBackgroundMessageHandler((payload) => {
    console.log("[firebase-messaging-sw.js] Received background message ", payload);
    // Customize notification here
    const notificationTitle = "✅ Daily Commit 알림";
    const notificationOptions = {
      body: "아직 잔디를 심지 않으셨네요!",
      icon: "/firebase-logo.png",
    };

    return self.registration.showNotification(notificationTitle, notificationOptions);
  });
}
