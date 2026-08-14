<div align="center">

# 🏦 Banking Management System

*A console-based banking system built in core Java — a hands-on exercise in object-oriented design.*

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![OOP](https://img.shields.io/badge/OOP-Design-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-In%20Progress-yellow?style=for-the-badge)

</div>

---

## 📖 Overview

This project models a simplified bank with customers, multiple account types, and money transfers between accounts — with real business rules enforced through the class design itself, not just data storage. It was built specifically to practice **inheritance, polymorphism, interfaces, encapsulation, and custom exception handling** through a realistic, non-trivial domain.

---

## ✨ Features

- 🏦 Three account types with genuinely distinct behavior — **Savings**, **Current**, **Fixed Deposit**
- 📈 Interest calculation via a shared interface, implemented only by accounts that actually earn interest
- 💳 Overdraft support on Current accounts, up to a configurable limit
- 🔒 Maturity locking and full-withdrawal-only rule on Fixed Deposit accounts
- ⚠️ Custom checked exceptions for every distinct failure case — never a generic `Exception`
- 🔁 Money transfer between accounts that correctly stops on failure — a failed withdrawal never triggers a deposit
- ✅ Test scenarios covering both success and failure paths, with output verified by hand

---

## 🧠 OOP Concepts Demonstrated

| Concept | Where it lives |
|---|---|
| **Abstraction** | `Account` is an abstract base class |
| **Inheritance** | `SavingsAcc`, `CurrentAccount`, `FixedDepositAcc` extend `Account` |
| **Polymorphism** | `withdraw()` behaves differently per account type, called through a shared `Account` reference |
| **Interfaces** | `InterestBearing`, implemented only by interest-earning accounts — not forced onto `CurrentAccount` |
| **Encapsulation** | Balance and account number are private/protected, mutated only through validated methods |
| **Composition** | `Customer` holds a list of `Account`s; `Bank` holds a list of `Customer`s |
| **Custom exceptions** | Distinct exception types per failure reason, propagated to the caller instead of swallowed |

---

## 📂 Project Structure

```
BankingManagementSystem/
├── src/
│   └── BankingManagementSystem/
│       ├── Account.java                  # Abstract base class
│       ├── SavingsAcc.java                # Interest-bearing, minimum balance
│       ├── CurrentAccount.java            # Overdraft support
│       ├── FixedDepositAcc.java           # Locked until maturity, full withdrawal only
│       ├── InterestBearing.java           # Interface for interest-earning accounts
│       ├── Customer.java                  # Holds a customer's accounts
│       ├── Bank.java                      # Manages customers, lookup, and transfers
│       ├── InsufficientBalance.java       # Thrown on failed withdrawals
│       ├── FixedDepositException.java     # Thrown on invalid FD operations
│       ├── InvalidAccountException.java   # Thrown when an account isn't found
│       ├── InvalidCustomerException.java  # Thrown when a customer isn't found
│       └── BankApp.java                   # Entry point with test scenarios
├── .gitignore
└── README.md
```

---

## ▶️ How to Run

```bash
cd src
javac BankingManagementSystem/*.java
cd ..
java BankingManagementSystem.BankApp
```

---

## 🧩 Design Decisions Worth Noting

- `calculateInterest()` lives on an `InterestBearing` interface rather than on `Account` itself — so `CurrentAccount` isn't forced to implement a meaningless method that always returns zero.
- `Bank.transferMoney()` calls `withdraw()` before `deposit()` with **no surrounding try-catch** — a failed withdrawal stops execution immediately, and the receiving account is never credited.
- Money is handled with `BigDecimal`, not `double`, to avoid floating-point precision errors — the standard approach for anything involving currency.
- `FixedDepositAcc` overrides `deposit()` to throw `UnsupportedOperationException` rather than a checked exception, since `Account.deposit()` declares no checked exceptions and an override cannot widen that contract.

---


<div align="center">

**Author:** Gautham — built as a foundational project before moving into Spring Boot.

</div>
