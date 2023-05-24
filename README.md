Main
1. Create html set of questions with grading script.

Tech task
1. Get questions from text file (utf8)
    - get text
    - split on question blocks
    - parse question blocks to question object class

2. Store questions
    - save questions objects in file (bin) (serialize/deserialize)
    - save questions objects to text file (utf8)
    - save questions objects into DB (MySQL, SQLight)*

3. Create html set of questions with grading script.
    - get questions set (quantity, random selected)
    - wrap questions fields values to html tags (table)
    - wrap to standard html header
    - add scripts for grade (TypeScript template)
    - save file (html)

Now
1. Done all
2. Done 2.1, postponed 2.2*
3. Partly 3.1, recoding 3.2, done 3.3, in progress 3.4, done 3.5

Will be code
x. User Interface for application.
x. Validating parsed questions.
x. Create 'html' file with gradable scripts from questions list.
x. Collect random questions list.
x. Collect list of unique questions from txt and bin files.
x. Add storage DB. Create structure tables.
x. Read/Write questions object from/to DB (MySQL, SQLight).
