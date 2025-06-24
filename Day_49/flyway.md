# Flyway implementation in project
  flyway:
    enabled: true
# flyway_schema_history
we need Repair "flyway_schema_history"
## some code add in build.gradle file
plugins {
	id 'org.flywaydb.flyway' version '7.15.0' // JDK 11 compatible
}

flyway {
	url = 'jdbc:mysql://localhost:3306/core_dev_cmedhealth_com'
	user = 'root'
	password = 'Rubel1234@'
	locations = ['filesystem:src/main/resources/db/migration']
}

# now flyway Repair from terminal
## command is = ./gradlew flywayRepair
# Set which version execute.
  flyway:
    enabled: true
    target: 1
# now start again my project