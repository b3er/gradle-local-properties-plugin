# Gradle local.properties plugin

This read properties from ```local.properties``` file and merge them with project properties.

That's all.

### Usage

Build script snippet for use in all Gradle versions:
```
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    classpath "gradle.plugin.com.github.b3er.local.properties:local-properties-plugin:1.0"
  }
}

apply plugin: "com.github.b3er.local.properties"
```

Build script snippet for new, incubating, plugin mechanism introduced in Gradle 2.1:
```
plugins {
    id "com.github.b3er.local.properties" version "1.0"
}
```
