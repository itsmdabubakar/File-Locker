# 🔒 (File Locker)

**File Locker** is a powerful desktop cybersecurity tool built with **Java** and **JavaFX**. It allows users to encrypt and decrypt **any file type** (PDFs, Images, Videos, Text) using military-grade **AES-128** encryption.

## 🚀 Features
- **File Encryption:** Encrypts actual files (not just text), making them unreadable to unauthorized users.
- **Any File Type:** Works on `.jpg`, `.pdf`, `.mp4`, `.docx`, and more.
- **Secure Key Generation:** Uses **SHA-1** to derive a cryptographic key from your custom password.
- **Visual Status:** Color-coded status indicators (Green for success, Red for errors).
- **Non-Destructive:** Creates a new `.enc` file, keeping your original file safe during testing.

## 🛠️ Technologies Used
- **Language:** Java (JDK 17+)
- **GUI Framework:** JavaFX
- **Cryptography:** `javax.crypto` (AES/ECB/PKCS5Padding)
- **File I/O:** `java.nio.file` (Binary file handling)

## 💻 How to Use
1. **Launch the App:** Run `Main.java`.
2. **Lock a File:**
   - Click **📁 Choose File** and select any file from your computer.
   - Enter a **Password**.
   - Click **🔒 Encrypt**.
   - *Result:* A new file ending in `.enc` will be created (e.g., `image.jpg.enc`).
3. **Unlock a File:**
   - Select the `.enc` file.
   - Enter the **same password**.
   - Click **🔓 Decrypt**.
   - *Result:* The original file is restored (e.g., `image_decrypted.jpg`).

## 📸 Screenshots
**

## ⚠️ Disclaimer
**Use with caution.** If you lose your password, the encrypted files cannot be recovered. This tool is for educational purposes to demonstrate symmetric encryption concepts.

## 📬 Contact
- **Developer:** [Mohammed Abubakar]
- **GitHub:** (https://github.com/itsmdabubakar/File-Locker.git)
