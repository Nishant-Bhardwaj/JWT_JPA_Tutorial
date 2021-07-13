# JWT_JPA_Tutorial
Complete JWT and all JPA impl

# Added "spring.jpa.hibernate.ddl-auto= update", that creates tables automatically from Entity class:

# Added "spring.jpa.show-sql=true", it shows SQL query executed in application:

Hibernate: 
    
    create table auth_request (
       username varchar2(255 char) not null,
        upassword varchar2(255 char),
        primary key (username)
    )
    
    alter table auth_request 
       add token varchar2(255 char)
       
Hibernate: 
    
    create table complaint_record (
       complaint_id number(19,0) not null,
        complaint_assigned_to varchar2(255 char),
        complaint_body varchar2(255 char) not null,
        department_name varchar2(255 char),
        complaint_subject varchar2(255 char),
        status varchar2(255 char),
        user_id number(19,0) not null,
        primary key (complaint_id)
    )
Hibernate: 
    
    create table user_record (
       user_id number(19,0) not null,
        mobile number(19,0),
        department_name varchar2(255 char),
        user_email varchar2(255 char),
        username varchar2(255 char) not null,
        primary key (user_id)
    )
Hibernate: 
    
    alter table complaint_record 
       drop constraint UK_m2fwhmije71ei46kmsmb59fkh
       
Hibernate: 
    
    alter table complaint_record 
       add constraint UK_m2fwhmije71ei46kmsmb59fkh unique (user_id)
       
Hibernate: 
    
    create sequence complaint_sequence start with 1 increment by  1

Hibernate:
    
    create sequence user_sequence start with 1 increment by  1

Hibernate: 
    
    alter table complaint_record 
       add constraint FKlnj4nlqv99veg8rkyvh7itf8m 
       foreign key (user_id) 
       references user_record
