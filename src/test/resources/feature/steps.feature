#language:ru

#Функциональность: Авторизация
#
#  Сценарий: : Авторизация в личном кабинете (позитивный)
#    Пусть открыта страница с формой авторизации "http://localhost:9999"
#    Когда пользователь пытается авторизоваться с именем "vasya" и паролем "qwerty123"
#    И пользователь вводит проверочный код 'из смс' "12345"
#    Тогда происходит успешная авторизация и пользователь попадает на страницу 'Личный кабинет'


Функциональность: Перевод суммы

  Сценарий: Перевод суммы с одной карты на другую (позитивный)
    Пусть открыта страница с формой авторизации "http://localhost:9999"
    Когда пользователь пытается авторизоваться с именем "vasya" и паролем "qwerty123"
    И пользователь вводит проверочный код 'из смс' "12345"
    Когда пользователь переводит 5000 рублей с карты с номером "5559000000000002" на свою 1 карту с главной страницы,
    Тогда баланс его 1 карты из списка на главной странице должен стать 15000 рублей.


#  # пример параметризированного теста
#  Структура сценария: : Авторизация в личном кабинете (негативный)
#    Пусть открыта страница с формой авторизации "http://localhost:9999"
#    Когда пользователь пытается авторизоваться с именем <login> и паролем <password>
#    И пользователь вводит проверочный код 'из смс' <code>
#    Тогда появляется ошибка о неверном коде проверки
#    Примеры:
#      | login | password  | code  |
#      | "vasya" | "qwerty123" | "00000" |
#      | "vasya" | "qwerty123" | "11111" |