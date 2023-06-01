# SaveDuck :dollar: :baby_chick:

<p align="center">
  <img src="https://github.com/Daebore/Images/blob/main/Fotos%20SaveDuck/logo.png?raw=true" alt="drawing" width="300"/>
</p>
<p align="center">
  SaveDuck is an Android application whose purpose is to help people to control the money they spend and to save money.
</p>
<p align="center">
  This App is my final project for Cross-Platform Application Development.
</p>
<p align="center">
  The language programe used is Java and the design was made with XML.
  This app works with a SQLite database.
</p>

## 	:page_facing_up: Features 
- Personal **tracking** of the user's **income and expenses** :euro:

- Helps the user to **save money** :moneybag:

- Provides **visual data** about the **current financial status** :bar_chart:

- Sends emails to the user with a **summary of the financial situation** :email:

- Saves all the **incomes and expenses movements** :notebook:

- Allows the user to **check out all the financial movements** :mag_right:

- Very **easy to use** :baby:

## :iphone: Opening the App

Every time you enter the App, a Splash Screen will appear with a welcomed message, a duck sound and an animation. 

This screen only stands for two second and its only function is to greet the user.

## :man: :woman: Creating an account

The first time you use SaveDuck, you will have to fill a questionnaire with your personal data and to accept the  privacy policy and terms of use.

This data will be saved in a database, so if the account is created, this screen will never show up again.

## :house: Main page

This is the main screen where the next will be dispalyed:
  - The user's name.
  - The total amouth of income. 
  - The total amouth of expenses. 
  - The quantity of money saved. 

The saved money will be shown in red color in case the expenses has surpassed the income plus the initial balance.

Apart from that, there are also three bottons in this screen:
  - Add income: By pressing this button, the user will be able to add a new income.
  - Add Expense: By pressing this button, the user will be able to add a new spent.
  - View history: By pressing this button, the user will be able to see all the records registered in the App.

## :dollar: Recording an income

In this screen the user can add a new income with a concept (not required).

If the process has been successfully, a pop up will be sound alongside with a coin sound.

## :money_with_wings: Recording an spent

In this screen the user can add a new spent with a concept (not required).

If the process has been successfully, a pop up will be sound alongside with a ring sound.

## :bar_chart: :e-mail: Background

This screen is one of the more importance of the whole App.
It is composed of the next elements:
<ul>
  <li>A Pie Chart which shows the total amount of income, expenses and saved money. All the data is extracted from the Database.</li>
  <li>A summary of the user's financials situation which will displayed the same information from the Chart using numbers.</li>
    <li>Three buttons with different functionalities:</li>
    <ul>
      <li>Show income history: Allows the user to see all the income movements that have been recorded</li>
      <li>Show spending history: Allows the user to see all the expenses movements that have been recorded</li>
      <li>Send and email: Allows the user to send an email to him/herself with a summary of all the financial situation, alongside with the date</li>
    </ul>
  </ul>

## :euro: :notebook: Income history
This screen is created with all the income records on the Database and displays the date in which the income was made, the quantity and a concept (not necessary).

The more income registered in the DataBase, the more data is shown here. If there are so many of them, the user can simply scroll the screen up or down.

If there is no record to shown, a message will be shown.

## :money_with_wings: :notebook: Spending history

This screen is created with all the expense records on the Database and displays the date in which the income was made, the quantity and a concept (not necessary).

The more expenses registered in the DataBase, the more data is shown here. If there are so many of them, the user can simply scroll the screen up or down.

As we have seen previously if there is no record to shown, a message will be shown.

## :heavy_plus_sign: Additional info

- This App implements Data Biding, a modern and useful way of coding for Android which binds data sources from the provider (XML) and consumer (Java) together and synchronizes them, leading in a clenner code, a faster performance (replacing findViewById) and reducing bugs.

- All the pop up/toast messages which appears in the App when a determined action is made have been programmed with an additional Java class is order to customize the way they appear and to avoid bugs.

- The App's DataBase was created using a library of SQLite Called Room, which uses the singleton software design pattern to create or return a DataBase. Room allows the App to work with the table's records as objects and allows the user to create him/her CRUD methods and ivoking them.

- All the income/expenses records are saved in the DataBase using the current data and time as the Primary Key.

- To make sure no background process are running and consuming resources, every time the user moves to other screen, all the process from the previous one are finished.

## ✍️ Author
https://github.com/Daebore
