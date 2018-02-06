Используемые технологии: Servlet api, JDBC, postgresql. Для запуска приложения необходимо создать базу данных cookbook. username=user, password=password.


Для реализации сервиса необходимо спроектировать базу данных, в которой хранятся кулинарные рецепты, у каждого рецепта должно обязательно быть определено

название (не обязательно уникальное)
описание
перечень ингредиентов
непосредственно описание алгоритма приготовления
автор (пользователь, который добавил рецепт)
рейтинг
в базе должен присутствовать каталог категорий рецептов (например суп, второе, закуски и так далее), рецепт может входить в любое количество категорий

в базе должна храниться информация о зарегистрированных пользователях

у сервиса есть веб-интерфейс, позволяющий:

искать рецепты по названию, категории, ингредиентам, автору
добавлять новые рецепты (только зарегистрированным пользователям)
голосовать (+1/-1 к рейтингу) за рецепт (доступно только зарегистрированным пользователям)
регистрироваться
авторизоваться.
