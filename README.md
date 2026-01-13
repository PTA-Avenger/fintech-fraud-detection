# 🛡️ Fintech Fraud Detection System

> **Enterprise-grade financial analysis platform built with Spring Boot 3, Docker, and O(1) Algorithmic Optimization.**

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0-green)
![Docker](https://img.shields.io/badge/Docker-Containerized-blue)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)
![Build](https://img.shields.io/badge/Build-Passing-brightgreen)

## 📌 Project Overview
This full-stack application simulates a banking fraud detection pipeline. It allows users to upload high-volume transaction statements (CSV), processes them through a heuristic fraud engine, and visualizes flagged risks in real-time.

It was engineered to demonstrate **Microservices architecture**, **Data Structure optimization**, and **Containerization** best practices required for modern Fintech environments (e.g., Nedbank, Capitec, Entelect).

---

## 🚀 Key Features & Technical Highlights

### 1. ⚡ Algorithmic Optimization (The "Interview Hook")
* **Problem:** Scanning millions of transactions against a blacklist using a standard List results in `O(n)` time complexity, causing latency spikes.
* **Solution:** Implemented a **HashSet** data structure for the blacklist service.
* **Result:** Achieved **O(1) constant-time complexity** for merchant verification, ensuring the system remains performant regardless of dataset size.

### 2. 🐳 Fully Containerized Architecture
* The entire application (Spring Boot Backend + PostgreSQL Database) is orchestrated via **Docker Compose**.
* Solves the "works on my machine" issue—deployable to any environment with a single command.
* Uses a custom **Bridge Network** for secure inter-container communication.

### 3. 🛡️ Robust Data Ingestion
* Implements **Fault-Tolerant CSV Parsing** to handle "dirty data" (e.g., commas inside merchant names like *"Smith, Jones & Co"*).
* Uses `BigDecimal` for all monetary calculations to prevent floating-point arithmetic errors common in financial systems.

---

## 🛠️ Tech Stack

| Component | Technology | Reasoning |
| :--- | :--- | :--- |
| **Backend** | Java 17, Spring Boot 3 | Industry standard for enterprise banking APIs. |
| **Database** | PostgreSQL 15 | Relational integrity and complex querying capabilities. |
| **DevOps** | Docker & Docker Compose | Containerization for consistent environments. |
| **Testing** | JUnit 5 & Mockito | Unit testing coverage for fraud logic. |
| **Frontend** | HTML5 / JavaScript | Lightweight dashboard for data visualization. |
| **Tools** | Maven, Git, Postman | Dependency management and version control. |

---

## 🔌 API Endpoints

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/transactions/upload` | Uploads a CSV file, runs fraud checks, and persists data. |
| `GET` | `/api/transactions` | Retrieves all transaction history (Paginated). |

---

## ⚙️ How to Run Locally

### Option 1: The Docker Way (Recommended)
*Requires Docker Desktop installed.*

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/YOUR_USERNAME/fintech-fraud-detection.git](https://github.com/YOUR_USERNAME/fintech-fraud-detection.git)
    cd fintech-fraud-detection
    ```
2.  **Run the pipeline:**
    ```bash
    docker-compose up --build
    ```
3.  **Access the Dashboard:**
    Open `index.html` in your browser.

### Option 2: The Manual Way (Java + Local DB)
1.  Ensure PostgreSQL is running locally on port `5432`.
2.  Update `application.properties` with your local DB credentials.
3.  Run the Spring Boot app:
    ```bash
    mvnw spring-boot:run
    ```

---

## 🧪 Testing

The project includes a comprehensive JUnit test suite covering the fraud detection logic.

```bash
mvnw test

Test Coverage:

shouldFlagBlacklistedMerchant(): Verifies HashSet lookup.

shouldFlagHighValueTransaction(): Verifies Sliding Window/Threshold logic.

shouldApproveNormalTransaction(): Verifies false-positive avoidance.

👤 Author
Thato Mabena

Role: Final Year CS Undergraduate & Software Engineer

Focus: Backend Engineering, Fintech Systems, Cloud Native Apps

LinkedIn: [https://www.linkedin.com/in/thamsanqa-mabena-4565a3356?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app]
