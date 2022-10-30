# Планирование автоматизации тестирования.
## Перечень автоматизируемых сценариев.
*Возможность покупки тура двумя способами:*

*	Обычная оплата по дебетовой карте.
*	Уникальная технология: выдача кредита по данным банковской карты.

*Карты, представленные для тестирования (файл data.json):*
*	4444 4444 4444 4441, status APPROVED
*	4444 4444 4444 4442, status DECLINED
	
*Тестируемые СУБД:*
*	MySQL;
*	PostgreSQL.

##### Корректными данными при заполнении полей ввода следует считать:

Поле «Номер карты» - 16 цифр.

Поле «Месяц» - числовое значение от 01 до 12.

Поле «Год» - двузначное числовое значение (последние две цифры порядкового номера года, но не ранее текущего года).

Поле «Владелец» - буквенные символы на латинице, дефис, пробел.

Поле «CVC» - три цифры.

#### Позитивные сценарии:

*1 Оплата по дебетовой карте со статусом APPROVED:*
- 	Кликнуть по кнопке "Купить"
- 	Ввести в поле "Номер карты" - 4444 4444 4444 4441
- 	Ввести в поле "Месяц" - любое число от 01 до 12
- 	Ввести в поле "Год" - последние две цифры текущего года +1
- 	Ввести в поле "Владелец" - валидное значение (например Petrov Oleg)
- 	Ввести в поле "CVC/CVV" – трехзначное цифровое значение
- 	Кликнуть по кнопке "Продолжить"

Ожидаемый результат: появилось всплывающее окно "Операция одобрена Банком", В БД статус операции APPROVED.

*2 Оплата по дебетовой карте со статусом DECLINED:*
- 	Кликнуть по кнопке "Купить"
- 	Ввести в поле "Номер карты" - 4444 4444 4444 4442
- 	Ввести в поле "Месяц" - любое число от 01 до 12
- 	Ввести в поле "Год" - последние две цифры текущего года +1
- 	Ввести в поле "Владелец" - валидное значение (например Petrov Oleg)
- 	Ввести в поле "CVC/CVV" - трехзначное цифровое значение
- 	Кликнуть по кнопке "Продолжить"

Ожидаемый результат: появилось всплывающее окно "Ошибка! Банк отказал в проведении операции", в БД статус операции DECLINED.

*3 Оплата через кредит по данным карты со статусом APPROVED:*
-	Кликнуть по кнопке "Купить в кредит"
-	Ввести в поле "Номер карты" - 4444 4444 4444 4441
-	Ввести в поле "Месяц" - любое число от 01 до 12
-	Ввести в поле "Год" - последние две цифры текущего года +1
-	Ввести в поле "Владелец" - валидное значение (например Petrov Oleg)
-	Ввести в поле "CVC/CVV" - трехзначное цифровое значение
-	Кликнуть по кнопке "Продолжить"

Ожидаемый результат: появилось всплывающее окно "Операция одобрена Банком", в БД статус операции APPROVED.

*4 Оплата через кредит по данным карты со статусом DECLINED:*
-	Кликнуть по кнопке "Купить в кредит"
-	Ввести в поле "Номер карты" - 4444 4444 4444 4442
-	Ввести в поле "Месяц" - любое число от 01 до 12
-	Ввести в поле "Год" - последние две цифры текущего года +1
-	Ввести в поле "Владелец" - валидное значение (например Petrov Oleg)
-	Ввести в поле "CVC/CVV" - трехзначное цифровое значение
-	Кликнуть по кнопке "Продолжить"

Ожидаемый результат: появилось всплывающее окно "Ошибка! Банк отказал в проведении операции", в БД статус операции DECLINED.

#### Негативные сценарии.
*1 Оплата с невалидным номером карты:*
-	Кликнуть по кнопке "Купить"
-	Ввести в поле "Номер карты" - 4444 4444 4444
-	Ввести в поле "Месяц" - любое число от 01 до 12
-	Ввести в поле "Год" - последние две цифры текущего года +1
-	Ввести в поле "Владелец" - валидное значение (например Petrov Oleg)
-	Ввести в поле "CVC/CVV" – трехзначный номер
-	Кликнуть по кнопке "Продолжить"

Ожидаемый результат: уведомление об ошибки под полем «Номер карты». Форма не отправлена.

*2 Оплата по карте с невалидным значением месяца:*
-	Кликнуть по кнопке "Купить"
-	Ввести в поле "Номер карты" – 4444 4444 4444 4441
-	Ввести в поле "Месяц" - число 13
-	Ввести в поле "Год" - последние две цифры текущего года +1
-	Ввести в поле "Владелец" - валидное значение (например Petrov Oleg)
-	Ввести в поле "CVC/CVV" - трехзначный номер
-	Кликнуть по кнопке "Продолжить"

Ожидаемый результат: уведомление об ошибке под полем "Месяц". Форма не отправлена.

*3 Оплата по карте с истекшим сроком действия (год):*
-	Кликнуть по кнопке "Купить"
-	Ввести в поле "Номер карты" - 4444 4444 4444 4441
-	Ввести в поле "Месяц" - любое число от 01 до 12
-	Ввести в поле "Год" – последние две цифры текущего года -1
-	Ввести в поле "Владелец" - валидное значение (например Petrov Oleg)
-	Ввести в поле "CVC/CVV" - трехзначный номер
-	Кликнуть по кнопке "Продолжить"

Ожидаемый результат: уведомление об ошибке под полем «Год». Форма не отправлена

*4 Оплата по карте с невалидным значением поля «Владелец»:*
-	Кликнуть по кнопке "Купить"
-	Ввести в поле "Номер карты" - 4444 4444 4444 4441
-	Ввести в поле "Месяц" - любое число от 01 до 12
-	Ввести в поле "Год" - последние две цифры текущего года +1
-	Ввести в поле "Владелец" - 555555
-	Ввести в поле "CVC/CVV" - трехзначный номер
-	Кликнуть по кнопке "Продолжить"

Ожидаемый результат: уведомление об ошибке внизу поля «Владелец». Форма не отправлена

*5 Оплата по карте с некорректным значением CVC/CVV:*
-	Кликнуть по кнопке "Купить"
-	Ввести в поле "Номер карты" - 4444 4444 4444 4441
-	Ввести в поле "Месяц" - любое число от 01 до 12
-	Ввести в поле "Год" - последние две цифры текущего года +1
-	Ввести в поле "Владелец" - валидное значение (например Petrov Oleg)
-	Ввести в поле "CVC/CVV" - 99
-	Кликнуть по кнопке "Продолжить"

Ожидаемый результат: уведомление об ошибке внизу поля «CVC/CVV». Форма не отправлена

*6 Оплата по карте с незаполненными полями:*
-	Кликнуть по кнопке "Купить"
-	Оставить все поля пустыми
-	Кликнуть по кнопке "Продолжить"

Ожидаемый результат: уведомление об ошибках под каждым из полей. Форма не отправлена

*7 Кредит по карте с невалидным номером:*
-	Кликнуть по кнопке "Купить в кредит"
-	Ввести в поле "Номер карты" – 444 444 444
-	Ввести в поле "Месяц" - любое число от 01 до 12
-	Ввести в поле "Год" - последние две цифры текущего года +1
-	Ввести в поле "Владелец" - валидное значение (например Petrov Oleg)
-	Ввести в поле "CVC/CVV" – трехзначный номер
-	Кликнуть по кнопке "Продолжить"

Ожидаемый результат: уведомление об ошибке внизу поля «Номер карты». Форма не отправлена

*8 Кредит по карте с невалидным месяцем:*
-	Кликнуть по кнопке "Купить в кредит"
-	Ввести в поле "Номер карты" – 4444 4444 4444 4441
-	Ввести в поле "Месяц" - 15
-	Ввести в поле "Год" - последние две цифры текущего года +1
-	Ввести в поле "Владелец" - валидное значение (например Petrov Oleg)
-	Ввести в поле "CVC/CVV" – трехзначный номер
-	Кликнуть по кнопке "Продолжить"

Ожидаемый результат: уведомление об ошибке внизу поля «месяц». Форма не отправлена

*9 Кредит по карте с истекшим сроком действия (год):*
-	Кликнуть по кнопке "Купить в кредит"
-	Ввести в поле "Номер карты" - 4444 4444 4444 4441
-	Ввести в поле "Месяц" - любое число от 01 до 12
-	Ввести в поле "Год" - последние две цифры текущего года -1
-	Ввести в поле "Владелец" - валидное значение (например Petrov Oleg)
-	Ввести в поле "CVC/CVV" – трехзначный номер
-	Кликнуть по кнопке "Продолжить"

Ожидаемый результат: уведомление об ошибке внизу поля «Год». Форма не отправлена

*10 Кредит по карте с невалидным значением в поле «Владелец»:*
-	Кликнуть по кнопке "Купить в кредит"
-	Ввести в поле "Номер карты" - 4444 4444 4444 4441
-	Ввести в поле "Месяц" - любое число от 01 до 12
-	Ввести в поле "Год" - последние две цифры текущего года +1
-	Ввести в поле "Владелец" - 99999
-	Ввести в поле "CVC/CVV" – трехзначный номер
-	Кликнуть по кнопке "Продолжить"

Ожидаемый результат: уведомление об ошибке внизу поля «Владелец». Форма не отправлена

*11 Кредит по карте с некорректным значением CVC/CVV:*
-	Кликнуть по кнопке "Купить в кредит"
-	Ввести в поле "Номер карты" - 4444 4444 4444 4441
-	Ввести в поле "Месяц" - любое число от 01 до 12
-	Ввести в поле "Год" - последние две цифры текущего года +1
-	Ввести в поле "Владелец" - валидное значение (например Petrov Oleg)
-	Ввести в поле "CVC/CVV" – 12
-	Кликнуть по кнопке "Продолжить"

Ожидаемый результат: уведомление об ошибке внизу поля «CVC/CVV». Форма не отправлена

*12 Кредит по карте с незаполненными полями:*
-	Кликнуть по кнопке "Купить в кредит"
-	Оставить все поля пустыми
-	Кликнуть по кнопке "Продолжить"

Ожидаемый результат: уведомление об ошибке под каждым из полей. Форма не отправлена

## Перечень используемых инструментов с обоснованием выбора.
**Java 11** - язык программирования для написания автотестов.

**IntelliJ IDEA 2022.1.3 (Community Edition)** - интегрированная среда разработки программного обеспечения с поддержкой Java 11.

**Gradle** — система для автоматизации сборки приложений и сбора статистики об использовании программных библиотек.

**JUnit 5** - наиболее широко используемая среда тестирования для приложений Java.

**Git** - система управления версиями с распределенной архитектурой, обеспечивающая отслеживание изменений программного кода и управления ими.

**GitHub** - крупнейший веб-сервис для хостинга IT-проектов и их совместной разработки.

**Selenide** - библиотека для автоматизированного тестирования веб-приложений на основе Selenium WebDriver, позволяющая использовать CSS селекторы.

**Lombok** - позволяет автоматически преобразовать объемный Java-код в оптимизированную и лаконичную структуру.

**Docker** — программное обеспечение для автоматизации развёртывания и управления приложениями в средах с поддержкой контейнеризации, контейнеризатор приложений. Платформа позволяет быстрее тестировать и выкладывать приложения, запускать на одной машине требуемое количество контейнеров.

**Faker** - библиотека с открытым исходным кодом, которая генерирует данные искусственного наполнителя для приложения и его потребностей в тестировании.

**Allure** - фреймворк для создания отчетов о тестировании, для наглядного отображения прохождения тестов и ошибок.

## Перечень и описание возможных рисков при автоматизации.

•	отсутствие документации по описанию работы приложения;

•	сложность в поддержке актуального состояния тестов при изменении данных;

•	возможны проблемы из-за необходимости поддержки двух СУБД (MySQL и PostgreSQL);

•	сложность первого запуска SUT;

•	невозможность запуска веб-приложения и работы с ним;

•	увеличение времени работы из-за недостаточной мощности ПК /проблем с интернет-соединением и проч.


## Интервальная оценка с учётом рисков в часах.
Необходимое время на тестирование составляет 78-102 часов, с учетом рисков – 88-112 часов.

• Написание плана тестирования - 8ч.

• Подготовка и настройка проекта, установка необходимого ПО, запуск SUT - 8ч.

• Написание тестов - от 48ч до 72ч.

• Написание отчетов о тестировании - 8ч.

• Написание отчетов об автоматизации - 6ч.

## План сдачи работ.
Написание плана тестирования – 22.10.2022

Написание авто-тестов - от 7 до 14 дней после согласования плана тестирования.

Написание отчетов о тестировании и автоматизации – 1-2 дня после написания авто-тестов.
