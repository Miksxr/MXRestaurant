# Restaurant Management System (RMS)  
**Система автоматизации ресторанного бизнеса**  

## 📌 О проекте  
Приложение для управления рестораном с тремя ролями пользователей:  
- **Посетители** - бронирование столов, заказ еды  
- **Официанты** - управление заказами, формирование чеков  
- **Администраторы** - аналитика, управление персоналом  

## 🛠 Технологический стек  
### Backend  
- **Supabase** (PostgreSQL):  
  - Хранение данных  
  - Аутентификация  
  - Realtime-обновления  

### Frontend (Android)  
- **Jetpack Compose** - современный UI  
- **Koin** - DI-контейнер  

## 🗃 Основные сущности БД  
```sql
Tables (id, number, capacity, status)  
Users (id, name, role, auth_id)  
MenuItems (id, name, price, category, stock)  
Orders (id, table_id, waiter_id, status, total)  
Reservations (id, table_id, user_id, date, guests)  
```

## 🔄 Ключевые процессы  
### Для посетителей:  
1. Просмотр свободных столов  
2. Бронирование с выбором даты/времени  
3. Онлайн-заказ блюд с оплатой  

### Для официантов:  
1. Просмотр закрепленных столов  
2. Управление заказами (добавление/удаление позиций)  
3. Формирование и печать чеков  

### Для администраторов:  
1. Аналитика бронирований  
2. Отчеты по эффективности официантов  
3. Управление меню и остатками  

## 🚀 Запуск проекта  
1. Клонировать репозиторий  
2. Настроить подключение к Supabase в `local.properties`:  
```
supabase.url=ВАШ_URL  
supabase.key=ВАШ_КЛЮЧ  
```
3. Собрать проект через Android Studio  

## 📄 Документация API  
Доступна по адресу: `/api-docs` после запуска  

## 🐛 Отчеты об ошибках  
Пожалуйста, создавайте issues в репозитории с подробным описанием:  
1. Шаги для воспроизведения  
2. Ожидаемое поведение  
3. Фактический результат  

## 🤝 Участие в разработке  
1. Форкните репозиторий  
2. Создайте ветку для вашей фичи (`feature/ваша-фича`)  
3. Отправьте Pull Request с описанием изменений  

## 📜 Лицензия  
MX Studio Maxim Cardinal
