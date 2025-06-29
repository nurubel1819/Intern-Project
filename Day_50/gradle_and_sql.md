## This project is Multi-module project
### Explanation: Here present one Main/Root project and multiple sub-module. Every sub-module have own buld.gradle file and own dependency.
Root build.gradle = Here keep common configuration.
setting.gradle = Here declares sub-module
MyProject/
├── build.gradle             ← root project's Gradle
├── settings.gradle          ← declares all sub-projects/modules
├── module-a/
│   └── build.gradle         ← module A's Gradle file
├── module-b/
│   └── build.gradle         ← module B's Gradle file
└── common-library/
    └── build.gradle         ← shared/common module

## SQL
here 
pm = prescribed_medications
m = medicines 
1.
where (case
           when pm.medicine_id is not null then m.manufacturer_name = :manufacturerName
           when pm.item_id is not null then item.manufacturer_name = :manufacturerName
    end)
2.
where (m.manufacturer_name = :manufacturerName or item.manufacturer_name = :manufacturerName)
difference between(1 and 2)
- in first sql can't always execute all statement. But second sql execute both.
- in first sql check prescribed_medications first then check medicines table but second sql can't check prescribed_medications table