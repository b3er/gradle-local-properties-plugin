package com.github.b3er.gradle.plugin.local.properties

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Plugin class, it reads "local.properties"
 * file from project dir and merge them with existing properties
 */
class LocalPropertiesPlugin implements Plugin<Project> {
  @Override
  void apply(Project project) {
    def localPropertiesFile = project.file("local.properties")
    if (localPropertiesFile.exists()) {
      localPropertiesFile.withInputStream {
        def properties = new Properties()
        properties.load(it)
        properties.stringPropertyNames().each {
          project.ext[it] = properties.getProperty(it)
        }
      }
    }
  }
}
