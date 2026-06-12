class Address:
    def __init__(self, street, city, zipCode):
        self.street = street
        self.city = city
        self.zipCode = zipCode

    def __str__(self):
        return f"{self.street}, {self.city} - {self.zipCode}"


class Student:
    def __init__(self, name, age, address, courses=None):
        self.name = name
        self._age = 0
        self.age = age  # Uses property validation
        self.address = address  # Composition (HAS-A relationship)
        self.courses = courses if courses is not None else []

    @property
    def age(self):
        return self._age

    @age.setter
    def age(self, value):
        if not isinstance(value, int) or value <= 0 or value > 120:
            raise ValueError("Age must be between 1 and 120.")
        self._age = value

    def add_course(self, course):
        self.courses.append(course)

    def display(self):
        print("Name:", self.name)
        print("Age:", self.age)
        print("Address:", self.address)
        print("Courses:", ", ".join(self.courses))


class ScholarshipStudent(Student):
    def __init__(self, name, age, address, scholarshipAmount, courses=None):
        super().__init__(name, age, address, courses)
        self.scholarshipAmount = scholarshipAmount

    def display(self):
        super().display()
        print("Scholarship Amount:", self.scholarshipAmount)


# Driver Code
address1 = Address("MG Road", "Guwahati", "781001")

student1 = ScholarshipStudent(
    "Purabi",
    20,
    address1,
    50000,
    ["Python", "Data Structures"]
)

# Mutable list behavior
student1.add_course("OOP")

student1.display()
