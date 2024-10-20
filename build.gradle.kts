import com.diffplug.gradle.spotless.SpotlessExtension
import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.gradle.plugins.ide.idea.model.IdeaLanguageLevel

plugins {
    idea
    id("io.spring.dependency-management")
    id("name.remal.sonarlint") apply false
    id("com.diffplug.spotless") apply false
}

idea {
    project {
        languageLevel = IdeaLanguageLevel(17)
    }
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

allprojects {
    group = "guru.qa"
    repositories {
        mavenLocal()
        mavenCentral()
    }
    val springframeworkBoot: String by project
    val jupiter: String by project
    val selenide: String by project

    plugins.apply("io.spring.dependency-management")
    dependencyManagement {
        dependencies {
            imports {
                mavenBom("org.springframework.boot:spring-boot-dependencies:$springframeworkBoot")
                mavenBom("org.junit:junit-bom:$jupiter")
            }

            dependency("com.codeborne:selenide:$selenide")
        }

        configurations.all {
            resolutionStrategy {
                eachDependency {
                    if (requested.group == "org.seleniumhq.selenium") {
                        useVersion("4.21.0")
                    }
                }

                failOnVersionConflict()

                force("com.google.guava:guava:31.1-jre")
                force("org.opentest4j:opentest4j:1.3.0")
                force("io.opentelemetry:opentelemetry-sdk-logs:1.25.0-alpha")
                force("io.opentelemetry:opentelemetry-semconv:1.25.0-alpha")
                force("com.google.code.findbugs:jsr305:3.0.2")
                force("org.sonarsource.sslr:sslr-core:1.24.0.633")
                force("org.eclipse.platform:org.eclipse.osgi:3.18.400")
                force("org.eclipse.platform:org.eclipse.equinox.common:3.18.0")
                force("org.apache.commons:commons-compress:1.26.1")
                force("commons-io:commons-io:2.15.1")
            }
        }
    }
}

subprojects {
    apply(plugin = "java")
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.compilerArgs.addAll(listOf("-Xlint:all,-serial,-processing"))
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    plugins.apply("name.remal.sonarlint")
    plugins.apply("com.diffplug.spotless")
    extensions.configure<SpotlessExtension> {
        java {
            palantirJavaFormat("2.38.0")
        }
    }
}

tasks.register("managedVersions") {
    doLast {
        project.extensions.getByType<DependencyManagementExtension>()
            .managedVersions
            .toSortedMap()
            .map { "${it.key}:${it.value}" }
            .forEach(::println)
    }
}