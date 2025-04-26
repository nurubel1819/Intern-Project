# learning activity
- Feedback on task from Session 02
- Q&A
## Understanding JPA/Hibernate Associations
## Unidirectional vs. Bidirectional Association
## Learing link : [click](https://www.baeldung.com/jpa-hibernate-associations)
# MY learning process
## Unidirectional Associations

### One-To-Many Relationship
@Entity
public class Department {
 
    @Id
    private Long id;
 
    @OneToMany
    @JoinColumn(name = "department_id") // this "department_id" column create in Employee table as a forign key
    private List<Employee> employees;
}

@Entity
public class Employee {
 
    @Id
    private Long id;
}

### Many-To-One Relationship
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;
}

@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}

### One-To-One Relationship
@Entity
public class Employee {
 
    @Id
    private Long id;
 
    @OneToOne
    @JoinColumn(name = "parking_spot_id") // This "parking_spot_id" column create in Employee table as a forign key
    private ParkingSpot parkingSpot;
 
}

@Entity
public class ParkingSpot {
 
    @Id
    private Long id;
 
}
### Many-To-Many Relationship
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors;

}

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}

## Bidirectional Associations
In a one-to-many bidirectional association, an entity has a reference to another entity. Additionally, the other entity has a collection of references to the first entity.
### One-To-Many Bidirectional Association
@Entity
public class Department {
 
    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
 
}
 
@Entity
public class Employee {
 
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
 
}
### Many-To-Many Bidirectional Association
@Entity
public class Student {
 
    @ManyToMany(mappedBy = "students")
    private List<Course> courses;
 
}
 
@Entity
public class Course {
 
    @ManyToMany
    @JoinTable(name = "course_student",
        joinColumns = @JoinColumn(name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;
 
}



