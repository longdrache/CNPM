# G8 Shoe Store — E-Commerce Website (CNPM)

A full-stack e-commerce web application for selling shoes, built as a university software engineering project (CNPM — Công Nghệ Phần Mềm) at Ho Chi Minh City University of Technology and Education (HCMUTE).

The application allows users to browse shoes, manage a shopping cart, register accounts with email verification, place orders, and track guest orders — all with a bilingual (Vietnamese/English) UI.

---

## Features

- **Product Catalog** — Browse shoes with images, prices (VND), sizes, and descriptions
- **Search & Filter** — Search by product name or filter by type (men/women)
- **Product Detail Pages** — View individual product details with size selection
- **User Registration** — Sign up with email verification (token-based activation link)
- **Authentication** — Login/logout with Spring Security form-based authentication
- **Session Shopping Cart** — Add items, edit quantity/size, remove items (stored in HTTP session)
- **Checkout & Orders** — Place orders as registered user or guest (with email tracking)
- **Guest Order Tracking** — Look up orders by email + bill code
- **Password Recovery** — Reset password via email token
- **Bilingual UI** — Vietnamese (default) and English via Spring i18n
- **Responsive Design** — Thymeleaf templates with custom CSS and JavaScript

---

## Tech Stack

| Technology | Purpose |
|------------|---------|
| Java 16 | Runtime |
| Spring Boot 2.5.1 | Core framework |
| Spring Data JPA | ORM / database access |
| Spring Security | Authentication & authorization |
| Thymeleaf | Server-side HTML templating |
| PostgreSQL | Relational database |
| Lombok | Boilerplate reduction |
| Gson | JSON serialization (cart API) |
| JavaMailSender | Email sending (Gmail SMTP) |
| Maven | Build tool |
| Docker | Containerization |

---

## Prerequisites

- JDK 16
- Apache Maven 3.6+
- PostgreSQL 12+
- (Optional) Docker for containerized deployment

---

## Installation & Setup

### 1. Database Setup

Create a PostgreSQL database named `shop`:

```sql
CREATE DATABASE shop;
```

Default connection settings (in `application.properties`):
- URL: `jdbc:postgresql://localhost:5432/shop`
- Username: `postgres`
- Password: `password`

Update these in `shop/src/main/resources/application.properties` if needed.

### 2. Email Configuration

Update `shop/src/main/resources/config/config.properties` with your Gmail SMTP credentials:

```properties
email=your-email@gmail.com
password=your-app-password
```

### 3. SSL Configuration

The app runs on **HTTPS port 8443** with a `localhost.p12` keystore (password: `password`, alias: `localhost`). The keystore file is included in the repository.

To run on plain HTTP instead, remove the SSL configuration from `application.properties`.

### 4. Build & Run

```bash
cd shop
mvn clean install
mvn spring-boot:run
```

Or run the JAR directly:

```bash
java -jar target/shop.jar
```

The app will be available at `https://localhost:8443`.

### 5. Docker (Optional)

```bash
cd shop
docker build -t shop .
docker run -p 8443:8443 -e PORT=8443 shop
```

---

## Project Structure

```
CNPM/
├── README.md
└── shop/
    ├── pom.xml                        # Maven build (Spring Boot 2.5.1, Java 16)
    ├── Dockerfile                     # Docker image (openjdk:16-jdk-alpine)
    └── src/main/
        ├── java/com/hcmute/shop/
        │   ├── ShopApplication.java          # Spring Boot entry point
        │   ├── Configuration/                # Security, mail, i18n config
        │   ├── Controller/                   # REST controllers
        │   ├── Service/                      # Business logic
        │   ├── DAO/                          # Spring Data JPA repositories
        │   └── Model/                        # JPA entities & DTOs
        └── resources/
            ├── application.properties       # App config (DB, SSL, JPA)
            ├── config/config.properties      # Gmail credentials
            ├── home_vi.properties           # Vietnamese i18n messages
            ├── home_en.properties           # English i18n messages
            ├── templates/                   # Thymeleaf HTML templates
            └── static/
                ├── image/                   # Product images
                ├── js/                      # JavaScript files
                └── style/                   # CSS files
```

---

## API Endpoints

### Home & Products

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | Home page — product listing; supports `?search=` for name search or `?search=men`/`?search=women` for type filter |
| GET | `/info/{id}` | Product detail page by UUID |

### Authentication & Account

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/signup` | Register new user; sends verification email |
| GET | `/signup/{email}/{code}` | Confirm signup token (5-min expiry) |
| POST | `/forget?email=` | Send password reset email |
| GET | `/forget/{code}/{token}` | Confirm password reset token |
| POST | `/verify?email=` | Resend verification email |
| GET | `/verify/{email}/{code}` | Confirm re-sent verification token |
| POST | `/destroy` | Logout (invalidate session) |
| POST | `/changepassword?passold=&passnew=&id=` | Change password (logged-in user) |
| POST | `/changepassf?password1=&password2=&id=` | Set new password after reset |

### Cart & Checkout

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/cart?id=&action=` | View cart; `action=delete` removes item |
| POST | `/cart?id=&quantity=&size=` | Add item to cart (or update if exists) |
| POST | `/cartinfo` | Returns cart as JSON |
| GET | `/checkout` | Checkout page |
| POST | `/pay` | Place order — saves bill, cart snapshot, and items |
| GET | `/thank` | Order confirmation page |

### Order Tracking

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/order` | Guest order lookup page |
| POST | `/forder?code=&email=` | Look up guest order by bill code + email |

---

## Database

### Tables

| Table | Description |
|-------|-------------|
| `users` | User accounts (UUID PK, email, password, role, active status) |
| `shoes` | Products (UUID PK, name, mark, price, type, info) |
| `wsize` | Shoe sizes with quantity per product |
| `image` | Product images |
| `bill` | Orders (UUID PK, receiver info, payment method, status) |
| `cart_buy` | Persisted cart snapshot at checkout |
| `shoes_buy` | Individual purchased shoe line items |
| `guest_bill` | Guest order tracking (email + bill) |
| `mail` | Email verification tokens |

### JPA Entities

- `User` — User account with UUID, email, BCrypt-hashed password, role
- `Shoes` — Product with name, brand, price, type, description
- `Wsize` — Size and quantity per shoe
- `Image` — Product image references
- `Bill` — Order with receiver name, address, phone, payment method, status
- `CartBuy` — Cart snapshot at checkout (total quantity, price, shipping)
- `ShoesBuy` — Purchased shoe line items
- `GuestBill` — Guest order tracking
- `Mail` — Email verification tokens

---

## Security

- **Spring Security** with form-based authentication
- **BCryptPasswordEncoder** (strength 10) for password hashing
- **Remember-me** enabled (7-day token validity)
- **Account activation** via email verification link
- **Password reset** via email token (5-minute expiry)
- **CSRF disabled** (for development purposes)
- **HTTPS** on port 8443 with PKCS12 keystore

---

## Payment Methods

- **Cash on Delivery** (CASH)
- **PayPal** (PAYPAL)

---

## Currency

Prices are displayed in **Vietnamese Dong (VND)** with a helper to convert to USD.

---

## Contributing

This is a university course project. Contributions are not expected, but feel free to fork and learn from the code.

---

## License

This project is for educational purposes. No license is specified.
