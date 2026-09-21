# Build notes

This repository intentionally does not include a Gradle wrapper JAR.

In Android Studio:
1. Open the project.
2. Let Android Studio import/sync Gradle.
3. Use JDK 17.
4. Install Android SDK Platform 37 if it is not installed.
5. Sync Project with Gradle Files.
6. Run the `app` configuration on an emulator/device.

For command-line builds, generate/use a Gradle 9.6 wrapper in your environment.
