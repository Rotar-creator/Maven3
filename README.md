# Mini Bank
**Сложность**: L - XL

Реализовать свой "Мини банк"

## Минимальные требования (сложность: L)
- Регистрация и хранение клиентов;
- Открытие / закрытие счетов, баланс счетов;
- Транзакции (траты и пополнения);

### Доп. требования (сложность: XL)
- Выделить функциональность в сервисы:
  - Сервис "Клиенты";
  - Сервис "Счета";
  - Сервис "Транзакции".

## Рекомендации 
- Используйте Spring Boot;
- Для автоматического тестирования используйте Spring Boot Test;
- Для развертывания инфраструктуры в тестах используйте [testcontainers](https://www.testcontainers.org/);
- Для развертывания необходимой инфраструктуры используйте Docker Desktop;
- Пишите JavaDoc.

## DoD
- Написан код, согласно требованиям;
- Форк от текущего репозитория, оформление PR;
- Состояние хранится в БД (можно H2);
- Вся функциональность покрыта Feature-тестами (при необходимости Unit);
- Документация (описание вашего решения в текущем файле, см. секцию ниже)

# Документация решения (инструкция и описание)
MiniBank - это web проект
Все данные хранятся в оперативке, реализацию сохранинения в БД H2 не успел реализовать еще бы пару дней

Для запуска проекта:
1. открыть проект в IntelliJ IDEA и запустить проект
2. В баузере отрыть адрес http://localhost:8080/
3. На главном окне одна кнопка - "список клиентов"
4. Справочник клиентов, чтобы перейти в необходимо нажать на кнопку "Список клиентов"
	4.1. Для создания нового клиента нажать кнопку "Создать нового клиента"
		4.1.1. Заполнить все поля, обязательное поле ИНН
		4.1.2. Для сохранениния нажать кнопку "Отправить"
		4.1.3. При успешном сохранении открывается окно подтверждения с возможностью вернуться в список
	4.2. Для редактирования реквизитов клиента, необходимо нажать на кнопку "редактирования" рядом с выбранным клиентом
	4.3. Для просмотра счетов клиента, необходимо нажать на кнопку "Счета клиента" рядом с выбранным клиентом
		
5. Справочник счетов клиентов:
	5.1. Для создания нового счета, необходимо нажать кнопку "открыть новый счет"
		Счет открывается автоматически для текущего клиента
		При успешном сохранении открывается окно подтверждения с возможностью вернуться в список счетов

	5.2. Для просмотра реквизитов счета, можно нажать кнопку "просмотр"
		Откроется окно с реквизитами счета в формате json
		Чтобы вернутся назад, можно в браузере нажать "назад"
	
	5.3. Для просмотра операций по счету, необходимо нажать на кнопку "Операции по счету"
		Будет открыто окно с операциями по счету
	
	5.4. Для зачисления на счет, необходимо нажать кнопку "Выполнить зачисление"
		На счет автоматически зачисляется тестовая сумма = 100
		При успешном выполнении операции открывается окно подтверждения с возможностью вернуться в список счетов

	5.5. Для списания со счета, необходимо нажать кнопку "Выполнить списание"
		Со счета автоматически списывается тестовая сумма = 100
		При успешном выполнении операции открывается окно подтверждения с возможностью вернуться в список счетов

Проект состоит из 3- классов:
	Client - клиент
	Account - счет, у клиента может быть несколько счетов
	Transaction - Транзакция - операция по счету, у счета может быть несколько операций
Для этих классов реализованы Сервисы и Контроллеры	

Deploy:
1. Скачать Tomcat 8/9
	https://tomcat.apache.org/download-90.cgi
2. Распаковать и запустить tomcat\bin\startup.bat	
3. Зайти в браузере http://localhost:8080/ в tomcat, в меню Manager App (задать пользователей conf\tomcat-users.xml)
4. Выбрать "Развернуть" и куазать файл \minibank\target\bank-0.0.1-SNAPSHOT.war
5. Чтобы запустить приложение можно открыть браузер по ссылке:
	http://localhost:8080/bank-0.0.1-SNAPSHOT/
