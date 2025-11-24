# Kotlin Library Template [![CI](https://github.com/christian-draeger/kotlin-library-template/actions/workflows/build.yml/badge.svg)](https://github.com/christian-draeger/kotlin-library-template/actions/workflows/build.yml) ![Maven Central Version](https://img.shields.io/maven-central/v/codes.draeger/kotlin-library-template-example?logo=sonatype&label=Release)

An **opinionated Kotlin library template** designed to jumpstart your Kotlin library projects with ease. This template provides a robust and scalable foundation for library development, with built-in automation tools to guide you from initial setup to publishing and maintaining your library effortlessly.

## 🚀 Features

- **⏩️ Quick Start:** No complex setup required. Simply fork this template, adjust the settings, and start coding.
- **🧱 Modular Structure:** Designed from the ground up to support splitting your library into multiple smaller libraries, enabling more granular access and flexibility for users of your project.
- **🌈 Powered by Kotlin Multiplatform:** Offers full flexibility in choosing your target platforms
- **📦 Automated Publishing:** Fully configured Gradle setup for publishing to Maven Central or other package repositories.
- **🗽 CI independent:** Every crucial task like build, test, release, linting, coverage measurement and automatic dependency updates can be executed manually via Gradle tasks.
- **🔋 Ready to go:** Everything you need to get started with your library included:
    - [x] [Explicit API mode](https://kotlinlang.org/docs/whatsnew14.html#explicit-api-mode-for-library-authors) to enforce visibility to be specifically declared.
    - [x] Automatic versioning following [Semantic Versioning](https://semver.org/) principles.
    - [x] [Gradle version catalog](https://docs.gradle.org/current/userguide/version_catalogs.html) for easy and clean cross module dependency management.
    - [x] [Gradle convention plugins](https://docs.gradle.org/current/samples/sample_convention_plugins.html) for consistent and reusable project configuration.
    - [x] Automatic dependency version checks and updates via [Gradle Versions Plugin](https://github.com/littlerobots/version-catalog-update-plugin).
    - [x] static code analysis and linting via [detekt](https://detekt.dev/) and [ktlint](https://ktlint.github.io/).
    - [ ] [OSS Review Toolkit](https://oss-review-toolkit.org/ort/docs/intro) integration for automated license compliance checks.
    - [x] Testing setup and coverage reporting out of the box.
- **🤖 CI/CD Ready:** Preconfigured GitHub Actions workflows for seamless builds, releases and maintenance.
    - [x] Build, test and release snapshot version on every push to main.
    - [x] One-Click publish to Maven Central with automatic versioning.
    - [x] Scheduled automatic version catalog update updates via PR by using gradle task.

---

## 🛠️ Installation and Usage

Even though this template tries to reduce the necessary manual steps as much as possible, 
there are still some things you need to do, since you cannot inherit some settings from GitHub template repository.

Please follow the [initial setup guide](INITIAL_SETUP.md) to get started. It won't take more than a few minutes.

---

## 📄 License

This repository is licensed under the [MIT License](LICENSE).

---

With this template, you can focus on writing great code while the boilerplate tasks are taken care of. Happy coding! 🎉
