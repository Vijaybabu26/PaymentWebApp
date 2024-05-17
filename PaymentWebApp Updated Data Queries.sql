Create database PaymentWebApp;

Use PaymentWebApp; 

Create table User(
	UserId Int auto_increment,
    FirstName Varchar(25),
    LastName varchar(25),
    PhoneNo varchar(15) unique,
    EMailId varchar(200),
    DateOfBirth date,
    Address Varchar(100),
    PassWord varchar(25),
    CurrWalletBalance Double(8,2),
    Primary Key (UserId)
);
select * from user;
desc User;

Create Table BankAcctType(
	BankAcctTypeId int,
    BankAcctTypeCode Varchar(3),
    BankAcctTypeDesc Varchar(50),
    primary key (BankAcctTypeId)
);

Insert into BankAcctType value(1,'SA','SAVINGS');
Insert into BankAcctType value(2,'CA','CURRENT');
Insert into BankAcctType value(3,'SL','SALARY');
Insert into BankAcctType value(4,'LO','LOAN');

Select * from BankAcctType;
Desc BankAcctType;
Create table BankAccount(
	BankAcctNo Varchar(16) Not Null,
    BankAcctName Varchar(50),
    BankAcctTypeId int,
    BankIFSCCode varchar(10),
    UserId int,
    CurrBankBalance double(12,2),
    foreign key(BankAcctTypeId) references BankAcctType(BankAcctTypeId),
    foreign key(UserId) references User(UserId),
    Primary Key(BankAcctNo)
);
select * from BankAccount;
ALTER TABLE BankAccount
ADD CONSTRAINT fk_PhNo
FOREIGN KEY (PhoneNo) REFERENCES User(PhoneNo);

ALTER TABLE BankAccount ADD BankAcctPin varchar(20);
desc BankAccount;

Select BankAcctNo from BankAccount where BankAcctNo =1234;
desc BankAccount;
CREATE TABLE Transaction (
    TxnId varchar(45) NOT NULL,
    TxnDate datetime,
    TxnAmount DOUBLE,
    SourceType Enum ('BANKACCOUNT','WALLET'),
    DestType Enum ('BANKACCOUNT','WALLET'),
    TxnStatus enum('CREDIT','DEBIT'),
    PhoneNo varchar(15),
    FOREIGN KEY (PhoneNo) REFERENCES User(PhoneNo),
    PRIMARY KEY (TxnId)
);
DESC transaction;
--DROP TABLE TRANSACTION;
Select * from tRANSACTION;
Update BankAccount Set CurrBankBalance = CurrBankBalance + 100  where BankAcctNo = 1234 and UserId = 1;