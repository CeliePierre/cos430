# 🐾 Animal Shelter Management System

## 📌 Project Overview

The **Animal Shelter Management** system is a web application designed to streamline the process of managing shelter operations, including scheduling, volunteer coordination, and animal adoption. The project consists of a **frontend** and a **backend**, ensuring a seamless and efficient workflow for workers and volunteers.

## 🔥 Tech Stack

### **Frontend**

- **Framework:** React.js
- **Routing:** React Router
- **Authentication:** JWT-based authentication
- **API Calls:** Axios / Fetch API

### **Backend**

- **Framework:** Node.js with Express.js
- **Database:** MongoDB
- **Authentication:** JWT / OAuth
- **ORM (if applicable):** Prisma / Mongoose 
- **Cloud Storage:** AWS S3(for images and documents)

## 🏗️ System Architecture

1. **Frontend**:

   - Provides a **worker's interface** and **volunteer's interface**.
   - Handles **schedule updates, task assignments, and forms submission**.
   - Communicates with the backend via **RESTful APIs** or **GraphQL**.

2. **Backend**:
   - Manages **authentication and user roles** (workers, volunteers, admins).
   - Handles **data storage** for animals, schedules, and adoption forms.
   - Provides secure **APIs for frontend interaction**.
   - Implements **logging and monitoring** for system health.

## 📌 Key Features

### ✅ **User Roles & Authentication**

- Secure login for **workers** and **volunteers**
- Role-based access control

### 📆 **Scheduling & Tasks**

- Manage **shelter work schedules**
- Assign **tasks** to volunteers and workers

### 🐶 **Animal Management**

- Add, update, and remove animals from the system
- Track animal medical history & adoption status

### 📋 **Forms & Adoption Process**

- Submit **intake forms** for new animals
- Process **adoption applications**

### 📊 **Admin Dashboard (Optional)**

- View reports on **shelter activity**
- Manage **user roles and permissions**

## 🚀 Getting Started


### Frontend Setup

```sh
cd frontend
npm install
npm start
```

###  Backend Setup

1. Install dependencies run ```npm i```
2. create ```.env``` file in root directory.
3. in ```.env``` file pasting your mongo DB URI ```MONGO_URI=mongodb://localhost:27017/<<your DB name>>```
4. run the backend server ```npm run dev```
if everything is working, you should see ✅ MongoDB Connected

## 📡 API Endpoints

### 🧑 User Routes (`/users`)

| Method  | Endpoint       | Description              |
|---------|---------------|--------------------------|
| `GET`   | `/users`      | Fetch all users         |
| `GET`   | `/users/:id`  | Fetch a user by ID      |
| `POST`  | `/users`      | Create a new user       |
| `PUT`   | `/users/:id`  | Update user details     |
| `DELETE`| `/users/:id`  | Delete a user          |

### 🐶 Animal Profile Routes (`/animals`)

| Method  | Endpoint       | Description                        |
|---------|---------------|------------------------------------|
| `GET`   | `/animals`    | Fetch all animal profiles        |
| `GET`   | `/animals/:id` | Fetch an animal profile by ID   |

## 📌 Contributing

This is private project for COS430 class:

1. Fork the repository
2. Create a new branch (`feature/your-feature`)
3. Commit your changes
4. Open a pull request

---

Let’s build a better future for shelter animals! 🐾🚀
