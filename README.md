# Sweet Crumbs Bakery

## Overview

Sweet Crumbs Bakery is an Android-based checkout and billing application developed as part of the Albertsons case study assignment.

The application allows users to browse bakery products, add items to a cart, update quantities, apply promotional offers, calculate GST, and view a detailed final bill. The Android application communicates with a Node.js and Express backend through REST APIs.

The backend is deployed on Render, allowing the application to be installed and used on Android devices without requiring local backend setup.

---

## Features

- Browse bakery products with images and pricing
- Add products to cart
- Increase or decrease item quantities
- Remove items automatically when quantity reaches zero
- Automatic cart subtotal calculation
- 10% discount for eligible orders
- GST calculation
- Detailed bill summary including subtotal, discount, tax, and final amount
- Modern Android UI using Fragments and RecyclerView
- Backend deployed online using Render
- Tested on a physical Android device

---

## Technology Stack

### Android Application

- Java
- Android Fragments
- RecyclerView
- Retrofit
- Material Design Components

### Backend

- Node.js
- Express.js
- REST APIs
- JSON Data Exchange

### Deployment & Version Control

- Render (Backend Hosting)
- GitHub (Source Code Hosting)
- GitHub Releases (APK Distribution)

---

## How to Use the Application

### Option 1: Install APK on an Android Phone

A prebuilt APK is available in the **GitHub Releases** section of this repository.

Steps:

1. Download the latest APK from the Releases section.
2. Transfer the APK to an Android device if required.
3. Open the APK file.
4. Allow installation from unknown sources if prompted.
5. Install and launch the application.
6. Browse products, add items to the cart, and generate the final bill.

The application connects directly to the deployed backend hosted on Render.

---

### Option 2: Run from Android Studio

1. Clone the repository.

```bash
git clone https://github.com/ananthikaa/SweetCrumbsBakery.git
```

2. Open the project in Android Studio.
3. Allow Gradle synchronization to complete.
4. Ensure internet connectivity is available.
5. Run the application on an emulator or Android device.

The application is already configured to use the deployed backend.

---

### Backend Setup (Optional)

The backend is already deployed and does not need to be run locally for normal usage.

For local testing:

```bash
cd backend
npm install
node server.js
```

Local backend URL:

```text
http://localhost:5000
```

Deployed backend URL:

```text
https://sweet-crumbs-bakery-backend.onrender.com/
```

---

## Offer Logic

- A 10% discount is applied when the cart subtotal is greater than or equal to ₹1000.
- GST is calculated after applying the discount.
- Final Amount = Subtotal − Discount + GST

---

## Screenshots

Application screenshots are available in the **screenshots** folder of this repository.

- Menu Screen – `screenshots/menu_screen.jpeg`
- Cart Screen – `screenshots/cart_screen.jpeg`
- Bill Screen – `screenshots/bill_screen.jpeg`

---

## Testing

The application was tested on a physical Android device using the deployed Render backend.

The following scenarios were verified:

- Product data loads successfully from the backend
- Products can be added to the cart
- Quantities can be increased and decreased
- Items are removed when quantity reaches zero
- Cart subtotal is calculated correctly
- Discount is applied when subtotal reaches ₹1000
- GST is calculated correctly
- Final bill displays accurate values
- APK installation and execution work successfully on a real Android device

---

## Assumptions

- Product data is predefined and served by the backend.
- A single promotional offer is supported.
- The discount policy is fixed at 10% for orders with a subtotal of ₹1000 or more.
- GST is calculated after discount application.
- Billing calculations are performed on the backend.
- Internet connectivity is required for communication with the deployed backend.
- User authentication, payment gateway integration, and order history are outside the scope of this case study.

---

## AI-Assisted Development

This project was developed using multiple AI-assisted development tools, including ChatGPT, Gemini AI (Android Studio), and GitHub Copilot (VS Code).

These tools were used throughout the development process for application planning, Android UI design, RecyclerView implementation, Retrofit integration, backend API development, billing logic implementation, debugging, deployment setup, Git/GitHub workflows, and documentation.

AI assistance helped accelerate development and problem-solving while enabling rapid iteration on both frontend and backend components. All generated suggestions were manually reviewed, customized, integrated, and tested before inclusion in the final solution.

The final application was validated through functional testing on a physical Android device using the deployed backend service.

---

## Repository Contents

- Android Application Source Code
- Node.js Backend Source Code
- Screenshots
- Documentation
- APK Download via GitHub Releases
