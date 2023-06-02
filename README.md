# SaveDuck :dollar: :baby_chick:

<p align="center">
  <img src="https://github.com/Daebore/Images/blob/main/Fotos%20SaveDuck/logo.png?raw=true" alt="drawing" width="320"/>
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

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Summary</summary>
  <ol>
    <li><a href="#Features">Features</a></li>
    <li><a href="#Opening-the-App">Opening the App</a></li>
    <li><a href="#Creating-an-account">Creating an account</a></li>
    <li><a href="#Main-page">Main page</a></li>
    <li><a href="#Recording-an-income">Recording an income</a></li>
    <li><a href="#Recording-a-spent">Recording an spent</a></li>
    <li><a href="#Financial-Background">Financial Background</a></li>
    <li><a href="#Income-history">Income history</a></li>
    <li><a href="#Spending-history">Spending history</a></li>
    <li><a href="#Additional-info">Additional info</a></li>
  </ol>
</details>

## 	:page_facing_up: Features 
<a name="Features"></a> 

- Personal **tracking** of the user's **income and expenses** :euro:

- Helps the user to **save money** :moneybag:

- Provides **visual data** about the **current financial status** :bar_chart:

- Sends emails to the user with a **summary of the financial situation** :email:

- Saves all the **incomes and expenses movements** :notebook:

- Allows the user to **check out all the financial movements** :mag_right:

- Very **easy to use** :baby:

<p align="right">(<a href="#top">Back to top</a>)</p>

## :iphone: Opening the App
<a name="Opening-the-App"></a> 

<p align="center">
  <img src="https://github.com/Daebore/Images/blob/main/Fotos%20SaveDuck/1splash.jpg?raw=true" alt="drawing" width="225" height="440"/>
</p>

Every time you enter the App, a Splash Screen will appear with a welcomed message, a duck sound and an animation. 

This screen only stands for two second and its only function is to greet the user.

<p align="right">(<a href="#top">Back to top</a>)</p>

## :man: :woman: Creating an account
<a name="Creating-an-account"></a> 

<p align="center">
  <img src="https://github.com/Daebore/Images/blob/main/Fotos%20SaveDuck/2create.jpg?raw=true" alt="drawing" width="225" height="440"/>
</p>

The first time you use SaveDuck, you will have to fill a questionnaire with your personal data and to accept the  privacy policy and terms of use.

This data will be saved in a database, so if the account is created, this screen will never show up again.

<p align="right">(<a href="#top">Back to top</a>)</p>

## :house: Main page
<a name="Main-page"></a> 

<p align="center">
  <img src="https://github.com/Daebore/Images/blob/main/Fotos%20SaveDuck/3main.jpg?raw=true" alt="drawing" width="225" height="440"/>
</p>

This is the main screen where the next will be dispalyed:
  - The user's name.
  - The total amouth of income. 
  - The total amouth of expenses. 
  - The quantity of money saved. 

The saved money will be shown in red color in case the expenses has surpassed the income plus the initial balance.

<p align="center">
  <img src="https://github.com/Daebore/Images/blob/main/Fotos%20SaveDuck/4mainneg.jpg?raw=true" width="225" height="440"/>
</p>

Apart from that, there are also three bottons in this screen:
  - Add income: By pressing this button, the user will be able to add a new income.
  - Add Expense: By pressing this button, the user will be able to add a new spent.
  - View history: By pressing this button, the user will be able to see all the records registered in the App.

<p align="right">(<a href="#top">Back to top</a>)</p>

## :dollar: Recording an income
<a name="Recording-an-income"></a> 

<p align="center">
  <img src="https://github.com/Daebore/Images/blob/main/Fotos%20SaveDuck/5income.jpg?raw=true" width="225" height="440"/>
</p>

In this screen the user can add a new income with a concept (not required).

If the process has been successfully, a pop up will be sound alongside with a coin sound.

<p align="right">(<a href="#top">Back to top</a>)</p>

## :money_with_wings: Recording a spent
<a name="Recording-a-spent"></a> 

<p align="center">
  <img src="https://github.com/Daebore/Images/blob/main/Fotos%20SaveDuck/6spent.jpg?raw=true" width="225" height="440"/>
</p>

In this screen the user can add a new spent with a concept (not required).

If the process has been successfully, a pop up will be sound alongside with a ring sound.

<p align="right">(<a href="#top">Back to top</a>)</p>

## :bar_chart: :e-mail: Financial Background
<a name="Financial-Background"></a> 

<p align="center">
  <img src="https://github.com/Daebore/Images/blob/main/Fotos%20SaveDuck/7back.jpg?raw=true" width="225" height="440"/>
</p>

This screen is one of the more importance of the whole App.
It is composed of the next elements:
<ul>
  <li>A Pie Chart which shows the total amount of income, expenses and saved money. All the data is extracted from the Database.</li>
  <li>A summary of the user's financials situation which will displayed the same information from the Chart using numbers.</li>
    <li>Three buttons with different functionalities:</li>
    <ul>
      <li>Show income history: Allows the user to see all the income movements that have been recorded.</li>
      <li>Show spending history: Allows the user to see all the expenses movements that have been recorded.</li>
      <li>Send and email: Allows the user to send an email to him/herself with a summary of all the financial situation, alongside with the date.</li>
    </ul>
  </ul>
    
  <p align="center">
  <img src="https://github.com/Daebore/Images/blob/main/Fotos%20SaveDuck/11mail.jpg?raw=true" width="225" height="440"/>
</p>
  
  <p align="right">(<a href="#top">Back to top</a>)</p>

## :euro: :notebook: Income history
<a name="Income-history"></a> 

  <p align="center">
  <img src="https://github.com/Daebore/Images/blob/main/Fotos%20SaveDuck/8showincome.jpg?raw=true" width="225" height="440"/>
</p>

This screen is created with all the income records on the Database and displays the date in which the income was made, the quantity and a concept (not necessary).

The more income registered in the DataBase, the more data is shown here. If there are so many of them, the user can simply scroll the screen up or down.

If there is no record to shown, a message will be shown.

<p align="center">
  <img src="https://github.com/Daebore/Images/blob/main/Fotos%20SaveDuck/9noshow.jpg?raw=true" width="225" height="440"/>
</p>

<p align="right">(<a href="#top">Back to top</a>)</p>

## :money_with_wings: :notebook: Spending history
<a name="Spending-history"></a> 

This screen is created with all the expense records on the Database and displays the date in which the income was made, the quantity and a concept (not necessary).

The more expenses registered in the DataBase, the more data is shown here. If there are so many of them, the user can simply scroll the screen up or down.

As we have seen previously if there is no record to shown, a message will be shown.

<p align="center">
  <img src="https://github.com/Daebore/Images/blob/main/Fotos%20SaveDuck/10showspent.jpg?raw=true" width="225" height="440"/>
</p>

<p align="right">(<a href="#top">Back to top</a>)</p>

## :heavy_plus_sign: Additional info
<a name="Additional-info"></a> 

- This App implements Data Biding, a modern and useful way of coding for Android which binds data sources from the provider (XML) and consumer (Java) together and synchronizes them, leading in a clenner code, a faster performance (replacing findViewById) and reducing bugs.

- All the pop up/toast messages which appears in the App when a determined action is made have been programmed with an additional Java class is order to customize the way they appear and to avoid bugs.

- The App's DataBase was created using a library of SQLite Called Room, which uses the singleton software design pattern to create or return a DataBase. Room allows the App to work with the table's records as objects and allows the user to create him/her CRUD methods and ivoking them.

- All the income/expenses records are saved in the DataBase using the current data and time as the Primary Key.

- To make sure no background process are running and consuming resources, every time the user moves to other screen, all the process from the previous one are finished.

<p align="right">(<a href="#top">Back to top</a>)</p>

## ✍️ Author
https://github.com/Daebore

<p align="right">(<a href="#top">Back to top</a>)</p>
