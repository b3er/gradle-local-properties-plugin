package com.github.b3er.gradle.plugin.local.properties

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class LocalPropertiesPluginTest {
  @Rule
  public TemporaryFolder projectRoot = new TemporaryFolder()
  Project project

  @Before
  void setup() throws IOException {
    def projectRootFolder = projectRoot.newFolder()
    def builder = new AntBuilder()
    builder.copy(file: 'src/test/resources/local.properties',
            tofile: "${projectRootFolder}/local.properties")

    project = ProjectBuilder
            .builder()
            .withName("testProject")
            .withProjectDir(projectRootFolder).build()
  }

  @Test
  void testAddProperties() {
    project.apply plugin: 'com.github.b3er.local.properties'
    Assert.assertTrue(project.hasProperty("LOCAL_PROPERTY"))
    Assert.assertEquals(project.property("LOCAL_PROPERTY"), "local_property")
  }
}
