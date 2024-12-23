rootProject.name = "qa-guru-homework"
include("hw01-git")
include("hw02-automation-practice-form")
include("hw03-selenide-first-lesson")
include("hw04-selenide-second-lesson")
include("hw05-java-first-lesson")
include("hw06-automation-practice-form-pageobject")

pluginManagement {
    val dependencyManagement: String by settings
    val johnrengelmanShadow: String by settings
    val sonarlint: String by settings
    val spotless: String by settings

    plugins {
        id("io.spring.dependency-management") version dependencyManagement
        id("com.github.johnrengelman.shadow") version johnrengelmanShadow
        id("name.remal.sonarlint") version sonarlint
        id("com.diffplug.spotless") version spotless
    }
}
