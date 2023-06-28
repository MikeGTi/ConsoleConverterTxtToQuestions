Program for convert text questions from text file(UTF8) to html page with questions assessment.

How to run:
java -jar C:\Temp\CreateQuestionsList.jar D:\Temp\questions_test.txt 19

first key  (D:\Temp\questions_test.txt) - text file path with questions text block, avoid russian symbols in.
second key (19) - number of questions need to be in assessment page

Supported question types:
- Multiple Choice
- Multiple Selection

Question text file create rules:
Question block starts from 'Question type', ends with blank row.

Required fields:
- Question type    -> 'Multiple Choice'
- Question number  -> '19'
- Question stem	   -> '����������� ������ �� ������� �� ������������� ��������� ����� ����'
- Question answers -> �. ������� �����
�. ����������� �������� ����� �� ����
- Question right answer -> Answer: �. (for Multiple Selection might be several values -> �, �)

Sample part of question text block:

Question type: Multiple Choice
Question title:
Question uuid: d85057bd-c728-419f-a6e2-90e28f454a23
19. ����������� ������ �� ������� �� ������������� ��������� ����� ����
    �. ������� �����
    �. ����������� �������� ����� �� ����
    �. ����������� �������� �����
    �. ��������� �����
    �. �����������
    �. ��� �����������������
    Answer: �.
    Difficulty: Easy
