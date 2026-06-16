# Sweet Crumbs Bakery

## Overview

Sweet Crumbs Bakery is an Android checkout and billing application developed as part of the Albertsons case study assignment.

The application allows users to browse bakery products, add items to a cart, apply promotional offers, calculate taxes, and view a detailed final bill.

---

## Features

- Browse bakery products
- Add and remove items from cart
- Update item quantities
- Automatic subtotal calculation
- 10% discount for orders above ₹1000
- GST calculation
- Detailed billing summary
- Product images and modern UI

---

## Technology Stack

### Android

- Java
- Android Fragments
- RecyclerView
- Retrofit
- Material Design Components

### Backend

- Node.js
- Express.js
- REST APIs

---

## Offer Logic

- Orders with subtotal greater than or equal to ₹1000 receive a 10% discount.
- GST is calculated after discount application.
- Final amount = Subtotal - Discount + Tax

---

## Project Structure

```text
app/        Android Application
backend/    Node.js Billing APIs
```

---

## API Endpoints

### Products

```http
GET /products
```

Returns product list.

### Billing

```http
POST /billing
```

Request:

```json
{
  "items": [
    {
      "productId": 1,
      "quantity": 2
    }
  ]
}
```

---

## Setup Instructions

### Backend

```bash
cd backend
npm install
node server.js
```

Server runs on:

```text
http://localhost:5000
```

### Android

1. Open project in Android Studio
2. Sync Gradle
3. Update BASE_URL if needed
4. Run application

---

## Screenshots

### Menu Screen

![Menu](screenshots/menu_screen.png)

### Cart Screen

![Cart](screenshots/cart_screen.png)

### Bill Screen

![Bill](screenshots/bill_screen.png)

---

## Assumptions

- Product catalog is predefined.
- Single promotional offer is supported.
- Tax rate is fixed in backend logic.
- Internet connectivity is available between app and backend.

---

## AI-Assisted Development

This project was developed using ChatGPT as an AI-assisted development tool. ChatGPT was used to assist with Android UI design, RecyclerView implementation, Retrofit integration, backend API development, billing logic, debugging, and GitHub repository setup.

The AI-assisted workflow helped accelerate development and resolve implementation issues efficiently while allowing manual customization and testing of the final solution.
