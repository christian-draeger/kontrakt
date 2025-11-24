# 🛠️ Installation and Usage

1. **Use the Template:**
    - Fork the repository or use it directly as a GitHub Template.
    - Customize `settings.gradle.kts` and `build.gradle.kts` to suit your project.
        - change package name
        - add or remove targets to the kotlin multiplatform extension in the [build.gradle.kts](build.gradle.kts)
    - Give Github Actions required permissions to be able to open pull requests and commit tags to your repository.
        - [x] Repository > Settings > Actions > General > Workflow permissions > Read and write permissions
        - [x] Repository > Settings > Actions > General > Workflow permissions > Allow GitHub Actions to create and approve pull requests

2. **Adjust Modules:**
    - Modify the `core` and `example` modules, or add new ones to expand functionality.

3. **Automated Releases:**
    - Use GitHub Actions for automated CI/CD workflows.
      - The GitHub action requires the following secrets to be set in your [project settings](settings/secrets/actions) to be able to publish to Maven Central:
        - `MAVEN_CENTRAL_USERNAME`
        - `MAVEN_CENTRAL_PASSWORD`
        - `SIGNING_IN_MEMORY_KEY`
        - `SIGNING_IN_MEMORY_KEY_ID`
        - `SIGNING_IN_MEMORY_KEY_PASSWORD`
      - You can find more information about how to get the required maven central credentials [here](https://medium.com/@iRYO400/how-to-upload-your-android-library-to-maven-central-central-portal-in-2024-af7348742247) or [here](https://medium.com/@efthymiou.dimitrios1/how-to-publish-your-library-to-maven-central-3923139967e1) or in the [official documentation](https://central.sonatype.org/register/central-portal/).
    - Publishing To Maven Local:
      - ```bash
        ./gradlew publishToMavenLocal
        ```

---

## 🤖 Preconfigured CI/CD Workflows

This template includes ready-to-use GitHub Actions workflows for:
- **Build & Test:** Ensures your library is reliable and bug-free.
- **Release:** Automatically publishes new versions when a release tag is created.
- **Linting:** Keeps your code clean and adherent to Kotlin standards.
- **Maintenance:** Automatic updates of your dependencies via Pull Request.
---

## ⚡️ Troubleshooting
### How to get the signing key
Please change the `<key id>` placeholder in the following command to your signing key id.
```bash
gpg --export-secret-keys --armor <key id> | grep -v '\-\-' | grep -v '^=.' | tr -d '\n'
```

---

## 📄 License

This repository is licensed under the [MIT License](LICENSE).

---

With this template, you can focus on writing great code while the boilerplate tasks are taken care of. Happy coding! 🎉
