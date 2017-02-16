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
    collectFiles(project).reverseEach { file ->
      if (file?.exists()) {
        file.withInputStream {
          def properties = new Properties()
          //noinspection GroovyAssignabilityCheck
          properties.load(it)
          properties.stringPropertyNames().each {
            project.ext[it] = properties.getProperty(it)
          }
        }
      }
    }
  }

  /**
   * Collect files from project and all its parents
   * @param project to collect files from
   * @return list of project files
   */
  private static def collectFiles(Project project) {
    def files = []
    while (project != null) {
      files.add project.file("local.properties")
      project = project.parent
    }
    return files
  }
}



