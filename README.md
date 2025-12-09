# UI Automation Project (Selenium + Appium)

## Стек и версии

- Java 11+, Maven
- Selenium 4.21.0, TestNG 7.10.2
- WebDriverManager 5.9.2
- Appium Java Client 9.3.0

## Структура

- `src/test/java/pages/web` – Page Object для sports.ru
- `src/test/java/tests/web` – веб-тесты (TestNG)
- `src/test/java/pages/mobile` – Page Object для Wikipedia (Appium)
- `src/test/java/tests/mobile` – мобильные тесты (TestNG)
- `src/test/resources/testng-web.xml`, `testng-mobile.xml` – suite-файлы
- `src/test/resources/config.properties` – базовые настройки (URL, браузер, Appium caps)

## Подготовка

1. Java 11+ и Maven установлены.
2. Для web: браузер Chrome установлен. WebDriverManager подтянет chromedriver сам.
3. Для mobile: запущен Appium Server (`http://127.0.0.1:4723/wd/hub`), доступен эмулятор/устройство Android.
   - Если Wikipedia уже установлена – достаточно `appPackage`/`appActivity`.
   - Если нужно ставить APK – пропишите путь в `appium.app` в `config.properties` или как `-Dappium.app=/path/to/wiki.apk`.

## Запуск

- Веб-тесты: `mvn -q test -P web`
- Мобильные тесты: `mvn -q test -P mobile`
  - Требуется активный Appium + запущенный эмулятор/устройство Android с Wikipedia.
  - Рекомендация (чтобы избежать промо-попапа “Wikipedia games”):
    1) Один раз вручную открыть приложение, нажать **Skip** онбординга и закрыть попап (кнопка X, id `org.wikipedia.alpha:id/closeButton`).
    2) Затем запускать с сохранением состояния:

       ```bash
       ANDROID_HOME=$HOME/Library/Android/sdk ANDROID_SDK_ROOT=$HOME/Library/Android/sdk \
       mvn -q clean test -P mobile \
         -Dappium.app=/absolute/path/to/app-alpha-universal-release.apk \
         -Dappium.automationName=UiAutomator2 \
         -Dappium.appPackage=org.wikipedia.alpha \
         -Dappium.appActivity=org.wikipedia.main.MainActivity \
         -Dappium.noReset=true
       ```

    - Альтернатива: стартовать сразу на поиске после ручного закрытия попапа:

       ```bash
       -Dappium.appActivity=org.wikipedia.search.SearchActivity
       ```

## Сценарии

### Web (sports.ru)

- Навигация в раздел «Футбол».
- Открытие первой новости «Футбол» и проверка заголовка.
- Вкладка «Матчи» в «Футбол» — наличие карточек матчей.
- Поиск по сайту «Месси» — проверка наличия результатов.

### Mobile (Wikipedia)

- Поиск «Selenium», открытие первой статьи, проверка заголовка.
- Поиск «Java», проверка, что результаты не пусты.
- Поиск «Appium», открытие статьи, прокрутка до текста «History».

## Параметры (можно переопределять через `-D`)

- `baseUrl` (web), `browser`, `headless` (optional)
- `appium.server.url`, `appium.platformName`, `appium.deviceName`, `appium.platformVersion`
- `appium.appPackage`, `appium.appActivity`, `appium.noReset`, `appium.app`

## Известные предупреждения

- SLF4J: используется no-op (можно добавить `slf4j-simple` при необходимости).
- Selenium CDP warning для Chrome 142 — не блокирует тесты.
