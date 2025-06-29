### Export database
mysqldump -u root -p -P 3307 database_name > export_database_name.sql
### Import database
mysql -u root -p -P 3307 new_database_name < export_database_name.sql