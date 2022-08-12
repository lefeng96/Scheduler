Drop table if exists corequisites,prerequisite,paths,classes, students;

-- scripts to create tables.
-- removed co and pre boolean expressions
-- for paths made taking into semester and year so its split in two and not just one.
create table students(fname varchar(255) not null, lname varchar(255)not null, cin char(9) not null Unique,admin boolean not null default false, primary key(cin));
create table classes(subject varchar(255) not null, classCode varchar(255)not null Unique,className varchar(255) not null,unit int not null, description varchar(5000), primary key(classCode));
create table paths(cin char(9)not null,classCode varchar(255)not null,semester varchar(255) not null,year varchar(255) not null, grade varchar(255) default '-', Foreign Key(cin) References students(cin), Foreign Key(ClassCode) References classes(classCode));
create table corequisite(course varchar(255)not null,corequisite varchar(255)not null, Foreign key(course)References classes(classCode));
create table prerequisite(course varchar(255)not null,prerequisite varchar(255)not null, Foreign key(course)References classes(classCode));
create table offered(course varchar(255),offered varchar(255), Foreign key(course)References classes(classCode));

create table  login(cin varchar(255) not null unique, email varchar(255), password varchar(255), FOREIGN key(cin) References students(cin));

-- add the missing values for the students and create some admins for the 
insert into students(fname,lname,cin) values('John','Johnson', '123456789');
insert into students(fname,lname,cin) values('Mike','Wazowski','132545893');
insert into students(fname,lname,cin) values('Leg', 'Legrand','546576879');
insert into students(fname,lname,cin,admin) values('Admin', 'Admiral','100000009',true);

insert into login values('123456789',"john@yahoo.com","john123");
insert into login values('132545893',"monster@google.com","mikeymike");
insert into login values('546576879',"leg@legger.org","legger");
insert into login values('100000009',"admin@csula.edu","adminForWeb");

--CS classes 
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 1010','Introduction to Higher Education for Computer Science Majors',3,"Skills required for the computing profession; critical thinking and lifelong learning; computer ethics; hands-on projects to explore the computing disciplines; academic success strategies; university structure, resources, policies, procedures; community engagement.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 1090','BASIC Programming',2,"Introduction to computer programming using BASIC language. Applications will assume a minimal mathematics background.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 1200','Living in a Technology-Based World',3,"Living and working with technology and its ethical and societal implications.  Understanding technology through knowledge of computing hardware, systems software, applications software, telecommunications, web pages, and APP development.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 1220','Introduction to Website Development',3,"Development of client-side web pages using hypertext markup language (HTML), Cascading Style Sheets (CSS). Javascript, and computer graphics and animation software.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 1222','Introduction to Relational Databases',3,"An introduction to relational databases and the SQL. You will do database design using Entity-Relationship, relational, and object-oriented data modeling along with database implementation");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 1550','Fundamentals for Computer Graphics',3,"Introduction to essential mathematics and implementations for Computer Graphics;  mathematics relevant to computer graphics, computer animation, and computer games.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 2010','Computer Programming Fundamentals',3,"Basic high level language programming concepts; procedural programming (methods, parameters, return values); basic control structures (sequence, if/else, for loop, while loop); arrays; introduction to defining objects.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 2011','Introduction to Programming I',3,"Introduction to algorithms; designing, coding, debugging, and documenting programs; implementation of algorithms as structured programs in a high level language; laboratory activities on problem analysis and software development.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 2012','Introduction to Programming II',3,"Algorithm development for Object Oriented Programming; inheritance, polymorphism, recursion, GUI basics; designing, coding, and documenting programs; laboratory activities on problem analysis and software development.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 2013','Programming with Data Structures',3,"Advanced programming techniques;  data structures such as linked lists, stacks, queues, trees, hash tables, skip lists, and graphs; sorting and searching algorithms; basic algorithm analysis; Laboratory activities and software development.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 2148','Discrete Structures',3,"An introduction to discrete mathematics with applications to Computer Science;  fundamentals of logic and set theory, counting techniques, relations, induction and recursion; graphs and trees; probability theory.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 2445','Introduction to Computer Systems',3,"Introduction to computer organization, Number systems and data representation; internal organization of a computer; primitive instructions and operations; logic gates; Operating Systems and Networks.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 2540','Special Topics in Computer Science',2,"Current topics of special interest to students in Computer Science, as announced in Schedule of Classes.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 2550','Introduction to Computer Graphics Tools',3,"Introduction to the state of the art computer graphic design software tools used for a variety of applications for graphic communication including web designs and visual effects.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 3034','Widely-Used Programming Languages',3,"Introduction to the most widely used contemporary programming languages such as C++, JavaScript, and Python.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 3035','Programming Language Paradigms',3,"Capabilities and styles of various programming languages;  functional programming; concurrent/reactive programming; constraint (logic) programming; rule-based programming; aspect-oriented programming; domain-specific languages.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 3112','Analysis of Algorithms',3,"Methods for the design and analysis of correct and efficient computer algorithms; applications to classical problems of searching, sorting, graph optimization and combinatorial optimization. ");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 3186','Introduction to Automata Theory',3,"Formal approach to automata theory; finite state machines, regular expressions, regular languages, context free languages and Turing machines. Develops mathematical foundation for computer science.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 3220','Web and Internet Programming',3,"Development of database-driven, multi-tiered, interactive web applications. HTML and CSS; processing HTTP requests and generating HTTP responses; session tracking; database access; web application architectures; server-side and client-side scripting languages.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 3337','Software Engineering',3,"Methodologies and tools for requirements analysis and design of large complex software system; process models, project planning, tracking, documentation, communication, and quality assurance; group laboratory project; oral and written presentations.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 3550','Game Development for Graphic Commucations',3,"Introduction to the theory and practice of video game design and programming using a high-level drag-drop-style game engine (e.g. the Unity3D Game Engine) and a scripting language (e.g. Javascript).");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 3555','Mobile Development for Graphic Communications',3,"Development of mobile applications on a selected platform such as iOS, Android, or Windows; creating basic and simple applications to demonstrate a majority features of the selected OS.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 3660','Complex Social and Economic Systems',3,"Computational approaches to modeling, simulation, and analysis of complex social and economic systems: statistics (frequentist and Bayesian); game theory, agent-based and stocks and flows modeling; networks; data science. ");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 3801','Societal and Ethical Issues in Computing',3,"Ethical theories (Western, Eastern, Feminist); societal implications of artificial intelligence, data science, social networks, and 3D printing; intellectual property, professional ethical standards, and the relationship between ethics and economics.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 3980','Cooperative Education',3,"Integration of work experience with academic program, individually planned through coordinator.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 4075','Concurrent and Distributed Programming',3,"Parallel programming techniques; abstract models of hardware and operating systems to support parallel programs; multiple models of concurrency; their advantages and disadvantages.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 4112','Competitive Programming',3,"Review of programming and algorithms in preparation for the ACM International Collegiate Programming Contest.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 4188','Compilers',3,"Compiler construction; syntax directed compiler study; organization of a compiler and overall design: parsing, semantic analysis, code generation and optimization.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 4220','Current Trends in Web Design and Development',3,"Current trends in client-side and server-side web design and development.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 4222','Priciples of Data Base Systems',3,"Database system architecture; disk and file management; buffer management; record file structures; database catalog; concurrency control; failure recovery; query processing; indexes; query optimization.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 4440','Introduction to Operating Systems',3,"Resource, memory and process management; concurrent processing; distributed systems; emphasis on some of the simple algorithms used to solve common problems encountered such as deadlocks, queue service, shared/distributed memory access.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 4470','Computer Networking Protocols',3,"Study of computer network layered architecture and protocols. Topics to be covered include: OPNET, network architecture, data link layer, addressing, LAN, network layer, transport layer and network applications.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 4471','Computer Networks Configuration and Management',3,"Network topology, architecture, and related software. Topics covered include designing a LAN and an internetwork, developing access lists, configuring routing protocols, customize switch configurations and manage device configurations.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 4540','Topics in Advance Computer Science',2,"Current topics of special interest to students in computer science, as announced in Schedule of Classes.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 4550','Computer Graphics',3,"Programming in object oriented graphics environment implementing primitive operations in two and three dimensions; image modeling using affine transformations; polygonal meshes and other topics.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 4551','Multimedia Software Systems',3,"Introduction to multimedia information and processing; topics: basic signal processing, color, space, formations of image, video, and audio data; current standards and the state-of-the-art techniques for multimedia systems.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 4555','Introduction to 3D Computer Game Programming',3,"3D game genre and styles; 3D game engines and their components; scripts; GUI; models; textures; sound and music; hands-on experience and rapid development.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 4556','Multiplayer Online Game Design and Development',3,"MMORP games from design through development and launch; real-time 3D graphics programming; network programming for multiplayer architecture; other issues related to game development and publishing.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 4635','Modeling and Simulation',3,"An examination of the four primary approaches to modeling and simulation; discrete event simulation; agent-based modeling; stock and flows modeling; actor-based modeling.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 4660','Artificial Intelligence',3,"Knowledge representation; problem solving strategies and search algorithms; applications from such areas as theorem proving, expert systems, natural language processing, robotics, and pattern recognition.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 4661','Introduction to Data Science',3,"Tools and techniques for extracting information from typically massive amounts of data and then visualizing the results.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 4780','Crytography and Information Security',3,"Principles and practice of cryptography and information security; basic concepts of cryptology, classical ciphers, modern symmetric ciphers, Advanced Encryption Standard, public key cryptography, data integrity and digital signature schemes.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 4961','Software Design Laboratory I',3,"Selection of a design project and develop a software system for a community organization or a corporate partner; determine customer needs; understand software solutions and profession in broader context.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 4962','Software Design Laboratory II',3,"Software design and implementation. Develop a software system for a community organization or a corporate partner; code and test system; Broader understanding of software engineering as well as professional ethics; project report and presentation.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 4963','Computer Science Recapitulation',3,"A recapitulation of the primary concepts of Computer Science; theory, programming, algorithms and systems; preparation for the Major Field Test.");
insert into classes(subject, classCode,className,unit,description)values ('Computer Science', 'CS 4990','Undergraduate Directed Study',3,"Project selected in conference with sponsor before registration; progress meetings held regularly, and a final report submitted.");


-- other subjects
insert into classes(subject, classCode,className,unit,description)values ('Physics', 'PHYS 2100','Mechanics and Thermodynamics',5,"Motion in one, two and three dimensions, Newton’s laws of motion, circular motion, work and energy, energy transfer, linear and angular momentum and their conservation, universal gravitation, and periodic motion. Lab and activity.");
insert into classes(subject, classCode,className,unit,description)values ('Physics', 'PHYS 2200','General Physics II, Electromagnetism and Optics',5,"Elementary field theory, basic electricity and magnetism, electromagnetic induction, DC, RC, and RLC circuits, Maxwell’s equations, and propagation of light. Lab and activity.");
insert into classes(subject, classCode,className,unit,description)values ('Math', 'MATH 2110','Calculus I',4,"Functions, graphs, limits, continuity, derivatives, applications of the derivative, anti-differentiation, definite integral, Fundamental Theorem of Calculus, integration by substitution, applications of the integral.");
insert into classes(subject, classCode,className,unit,description)values ('Math', 'MATH 2120','Calculus II',4,"Integration of transcendental functions, methods of integration, limits of sequences and series, power series, Taylor series, three dimensional analytic geometry.");
insert into classes(subject, classCode,className,unit,description)values ('Math', 'MATH 2550','Linear Algebra',3,"Vector spaces, linear transformations, linear equations, matrices, determinants, eigenvectors and eigenvalues, canonical forms.");
insert into classes(subject, classCode,className,unit,description)values ('Electrical Engineering', 'EE 3445','Computer Orginization',3,"Introduction to computer systems including implementation alternatives for major processor sub-systems.  Discussion includes data representation, central processing units, instruction formats, addressing modes, memory hierarchy, parallel processors, and RISC machines.");
insert into classes(subject, classCode,className,unit,description)values ('Math', 'MATH 1040','Precalculus',6,"Functions, Exponential and logarithmic functions; polynomials and rational functions; systems of linear equations and matrices; sequences and series; trigonometric functions, identities, and equations; solution of triangles; inverse trigonometric functions; complex numbers, DeMoivre’s Theorem; parametric equations; polar coordinates; conic sections.");

--GE Classes
insert into classes(subject, classCode,className,unit,description)values ('GE','GE A1','BLOCK A1',3,'Choose from the list of classes that meet this requirement.');
insert into classes(subject, classCode,className,unit,description)values ('GE','GE A2','BLOCK A2',3,'Choose from the list of classes that meet this requirement.');
insert into classes(subject, classCode,className,unit,description)values ('GE','GE B1','BLOCK B1',3,'Choose from the list of classes that meet this requirement.');
insert into classes(subject, classCode,className,unit,description)values ('GE','GE B2','BLOCK B2',3,'Choose from the list of classes that meet this requirement.');
insert into classes(subject, classCode,className,unit,description)values ('GE','GE C','BLOCK C',3,'Choose from the list of classes that meet this requirement.');
insert into classes(subject, classCode,className,unit,description)values ('GE','GE D1','BLOCK D1',3,'Choose from the list of classes that meet this requirement.');
insert into classes(subject, classCode,className,unit,description)values ('GE','GE D2','BLOCK D2',3,'Choose from the list of classes that meet this requirement.');
--English classes
insert into classes(subject, classCode,className,unit,description)values ('English','ENGL 1010','ACCELERATED COLLEGE WRITING',3,'Reading and Writing to develop and communicate ideas.  Instruction in strategies for planning, composing, and revising college writing, authorities, examples, arguments, and facts.');
insert into classes(subject, classCode,className,unit,description)values ('English','ENGL 2030','INTRODUCTION TO TECHNICAL WRITING',3,'Introduction to the methods of and practice in organizing, developing, and expressing technical information and ideas to a variety of audiences; emphasis on understanding the rhetorical situation and developing a clear style.');

-- change Paths cid to class acronym and number
--test data for pre existing paths
insert into paths values(123456789,'CS 1010','Spring','2018','-');
insert into paths values(123456789,'CS 1220','Spring','2018','-');
insert into paths values(132545893,'CS 3034','Spring','2018','-');
insert into paths values(132545893,'PHYS 2100','Fall', '2018','-');
insert into paths values(546576879,'Math 2550','Spring','2019','-');
insert into paths values(546576879,'CS 4990', 'Fall','2019','-');
insert into paths values(546576879,'CS 4188','Fall','2018','-');
insert into paths values(132545893,'CS 1222','Summer','2018','-');
insert into paths values(132545893,'CS 2540','Spring','2019','-');
insert into paths values(123456789,'CS 2011','Spring','2019','-');
insert into paths values(123456789,'CS 2012','Fall','2019','-');
insert into paths values(123456789,'CS 3112','Spring','2020','-');
insert into paths values(123456789,'CS 2550','Spring','2020','-');
insert into paths values(123456789,'CS 2013','Spring','2020','-');
insert into paths values(123456789,'MATH 2110','Spring','2020','-');
insert into paths values(123456789,'MATH 2120','Fall','2020','-');

--Prerequisite
insert into prerequisite values('CS 2011','MATH 1040');
insert into prerequisite values('CS 2012', 'CS 2011');
insert into prerequisite values('CS 2013', 'CS 2012');
insert into prerequisite values('CS 2148', 'CS 2012');
insert into prerequisite values('CS 2148', 'MATH 2120');
insert into prerequisite values('CS 2445', 'CS 2011');
insert into prerequisite values('CS 2550', 'CS 1550');
insert into prerequisite values('CS 3034', 'CS 2013');
insert into prerequisite values('CS 3034', 'CS 2148');
insert into prerequisite values('CS 3035', 'CS 2013');
insert into prerequisite values('CS 3035', 'CS 2148');
insert into prerequisite values('CS 3186', 'CS 2013');
insert into prerequisite values('CS 3186', 'CS 2148');
insert into prerequisite values('CS 3220', 'CS 2013');
insert into prerequisite values('CS 3337', 'CS 2013');
insert into prerequisite values('CS 3550', 'CS 1550');
insert into prerequisite values('CS 3555', 'CS 1550');
insert into prerequisite values('CS 3660', 'CS 2148');
insert into prerequisite values('CS 3801', 'CS 2011');
insert into prerequisite values('CS 4075', 'CS 3112');
insert into prerequisite values('CS 4075', 'CS 3035');
insert into prerequisite values('CS 4112', 'CS 3112');
insert into prerequisite values('CS 4188', 'CS 3112');
insert into prerequisite values('CS 4188', 'CS 3035');
insert into prerequisite values('CS 4188', 'CS 3186');
insert into prerequisite values('CS 4220', 'CS 3112');
insert into prerequisite values('CS 4220', 'CS 3220');
insert into prerequisite values('CS 4222', 'CS 1222');
insert into prerequisite values('CS 4222', 'CS 3112');
insert into prerequisite values('CS 4440', 'CS 2013');
insert into prerequisite values('CS 4470', 'CS 3112');
insert into prerequisite values('CS 4471', 'CS 4440');
insert into prerequisite values('CS 4550', 'CS 3112');
insert into prerequisite values('CS 4550', 'MATH 2550');
insert into prerequisite values('CS 4551', 'CS 3112');
insert into prerequisite values('CS 4555', 'CS 3112');
insert into prerequisite values('CS 4556', 'CS 3112');
insert into prerequisite values('CS 4556', 'CS 3220');
insert into prerequisite values('CS 4635', 'CS 3112');
insert into prerequisite values('CS 4635', 'CS 3660');
insert into prerequisite values('CS 4660', 'CS 3112');
insert into prerequisite values('CS 4661', 'CS 3112');
insert into prerequisite values('CS 4661', 'CS 3660');
insert into prerequisite values('CS 4780', 'CS 3112');
insert into prerequisite values('CS 4961', 'CS 3112');
insert into prerequisite values('CS 4961', 'CS 3220');
insert into prerequisite values('CS 4961', 'CS 3035');
insert into prerequisite values('CS 4961', 'CS 3337');
insert into prerequisite values('CS 4961', 'CS 3186');
insert into prerequisite values('CS 4961', 'CS 3801');
insert into prerequisite values('CS 4962', 'CS 4961');
insert into prerequisite values('CS 4963', 'MATH 2550');
insert into prerequisite values('CS 4963', 'PHYS 2200');
insert into prerequisite values('CS 4963', 'CS 4962');
insert into prerequisite values('CS 4963', 'EE 3445');
insert into prerequisite values('EE 3445', 'CS 2013');
insert into prerequisite values('MATH 2110', 'MATH 1040');
insert into prerequisite values('MATH 2110', 'MATH 1081');
insert into prerequisite values('MATH 2110', 'MATH 1083');
insert into prerequisite values('MATH 2120', 'MATH 2110');
insert into prerequisite values('PHYS 2100', 'MATH 2110');
insert into prerequisite values('PHYS 2200', 'PHYS 2100');

insert into prerequisite values('MATH 2550', 'MATH 2120');

--Corequisite
insert into corequisite values('CS 3034', 'CS 3112');
insert into corequisite values('CS 3035', 'CS 3112');
insert into corequisite values('CS 3660', 'CS 3112');
insert into corequisite values('CS 4963', 'CS 4962');
insert into corequisite values('CS 4963', 'CS 3112');

insert into prerequisite values('PHYS 2200', 'MATH 2120');


--Offered for Fall
insert into offered values ('CS 1010','Fall');
insert into offered values ('CS 1220','Fall');
insert into offered values ('CS 1222','Fall');
insert into offered values ('CS 1550','Fall');
insert into offered values ('CS 2010','Fall');
insert into offered values ('CS 2011','Fall');
insert into offered values ('CS 2012','Fall');
insert into offered values ('CS 2013','Fall');
insert into offered values ('CS 2148','Fall');
insert into offered values ('CS 2540','Fall');
insert into offered values ('CS 3034','Fall');
insert into offered values ('CS 3035','Fall');
insert into offered values ('CS 3112','Fall');
insert into offered values ('CS 3186','Fall');
insert into offered values ('CS 3220','Fall');
insert into offered values ('CS 3337','Fall');
insert into offered values ('CS 3890','Fall');
insert into offered values ('CS 4075','Fall');
insert into offered values ('CS 4188','Fall');
insert into offered values ('CS 4222','Fall');
insert into offered values ('CS 4440','Fall');
insert into offered values ('CS 4470','Fall');
insert into offered values ('CS 4540','Fall');
insert into offered values ('CS 4555','Fall');
insert into offered values ('CS 4660','Fall');
insert into offered values ('CS 4661','Fall');
insert into offered values ('CS 4780','Fall');
insert into offered values ('CS 4961','Fall');
insert into offered values ('CS 4990','Fall');
insert into offered values('MATH 1040','Fall');
insert into offered values('MATH 2110','Fall');
insert into offered values('MATH 2120','Fall');
insert into offered values('MATH 2550','Fall');
insert into offered values('PHYS 2100','Fall');
insert into offered values('PHYS 2200','Fall');


--Offered for Summer
insert into offered values('CS 3220','Summer');
insert into offered values('CS 4222','Summer');
insert into offered values('CS 4471','Summer');

--Offered 
insert into offered values('CS 1010','Spring');
insert into offered values('CS 1200','Spring');
insert into offered values('CS 1220','Spring');
insert into offered values('CS 2011','Spring');
insert into offered values('CS 2013','Spring');
insert into offered values('CS 2550','Spring');
insert into offered values('CS 3112','Spring');
insert into offered values('CS 3186','Spring');
insert into offered values('CS 4540','Spring');
insert into offered values('CS 4551','Spring');
insert into offered values('CS 4635','Spring');
insert into offered values('CS 4962','Spring');
insert into offered values('CS 4963','Spring');
insert into offered values('CS 4990','Spring');
insert into offered values('CS 2148','Spring');
insert into offered values('CS 3035','Spring');
insert into offered values('CS 3220','Spring');
insert into offered values('CS 3337','Spring');
insert into offered values('CS 3801','Spring');
insert into offered values('CS 3980','Spring');
insert into offered values('CS 4220','Spring');
insert into offered values('CS 4440','Spring');
insert into offered values('CS 4471','Spring');
insert into offered values('EE 3445','Spring');
insert into offered values('MATH 1040','Spring');
insert into offered values('MATH 2110','Spring');
insert into offered values('MATH 2120','Spring');
insert into offered values('MATH 2550','Spring');
insert into offered values('PHYS 2100','Spring');
insert into offered values('PHYS 2200','Spring');


/* 
create table login(cin char(9) not null, email varchar(255), password varchar(255)not null, firstLog boolean not null default 0, primary key(cin), Foreign Key(cin) References students(cin));
create table admin(username varchar(100), password varchar(20), admin boolean not null);
insert into admin values('admin','admin123',true); */

insert into students(fname,lname,cin) values('Carmen','Santiago', '123456799');
insert into students(fname,lname,cin) values('Jimmy','Page','132545894');
insert into students(fname,lname,cin) values('Lenny', 'Loosejocks','546576889');
insert into students(fname,lname,cin) values('Laura','Brehm', '123456788');
insert into students(fname,lname,cin) values('Cassidy','Ball','133545893');
insert into students(fname,lname,cin) values('Jude', 'Solt','546676879');
insert into students(fname,lname,cin) values('Mel','Downing', '113456789');
insert into students(fname,lname,cin) values('Leonard','DiCaprio','122545893');
insert into students(fname,lname,cin) values('Rima', 'Lemire','546556879');
insert into students(fname,lname,cin) values('Patricia','Ducksworth', '121456789');

insert into students(fname,lname,cin) values('Bobby','Hill','131545893');
insert into students(fname,lname,cin) values('Bobbert', 'Bobberson','566576879');
insert into students(fname,lname,cin) values('Karissa','Kirksy', '111456789');
insert into students(fname,lname,cin) values('Jackie','Jackson','132545593');
insert into students(fname,lname,cin) values('Jed', 'Axtell','544576888');
insert into students(fname,lname,cin) values('Vernie','Dease', '222456789');
insert into students(fname,lname,cin) values('Joy','Dillion','132515593');
insert into students(fname,lname,cin) values('Mathew', 'Polite','533576888');
insert into students(fname,lname,cin) values('Daniel','Northington', '131456789');
insert into students(fname,lname,cin) values('Jack','Armijo','132945593');

insert into students(fname,lname,cin) values('Naomi', 'Waterson','744576888');
insert into students(fname,lname,cin) values('Debra','Wilkinson', '111156789');
insert into students(fname,lname,cin) values('Laura','Mell','112545593');
insert into students(fname,lname,cin) values('Elmo', 'Kirk','144576888');
insert into students(fname,lname,cin) values('Johnny','Depp', '211453789');
insert into students(fname,lname,cin) values('Kyle','Elzy','232545293');
insert into students(fname,lname,cin) values('Hobert', 'Tao','244576088');
insert into students(fname,lname,cin) values('An','Rosier', '211456789');
insert into students(fname,lname,cin) values('Susan','Bolong','232545593');
insert into students(fname,lname,cin) values('Demetrius', 'Yurick','244576888');

insert into students(fname,lname,cin) values('Marleen','Swain', '211456782');
insert into students(fname,lname,cin) values('Clara','McGregor','232545592');
insert into students(fname,lname,cin) values('Desmond', 'Worman','244576882');
insert into students(fname,lname,cin) values('Paz','Danos', '211456783');
insert into students(fname,lname,cin) values('Malcolm','Bove','232545594');
insert into students(fname,lname,cin) values('William', 'Dowel','244576883');
insert into students(fname,lname,cin) values('Nina','Mines', '011456789');
insert into students(fname,lname,cin) values('Joel','Stell','032545593');
insert into students(fname,lname,cin) values('Isis', 'Godbold','044576888');
insert into students(fname,lname,cin) values('Lisabeth','Monarerez', '911456789');

insert into students(fname,lname,cin) values('Sunni','Worthy','932545593');
insert into students(fname,lname,cin) values('Larry', 'Bird','944576888');
insert into students(fname,lname,cin) values('Gracie','Blank', '811456789');
insert into students(fname,lname,cin) values('Holly','Montana','832545593');
insert into students(fname,lname,cin) values('Valerie', 'Geeter','844576888');
insert into students(fname,lname,cin) values('Blake','Smith', '811456788');
insert into students(fname,lname,cin) values('Deandre','Carter','832545598');
insert into students(fname,lname,cin) values('Kevin', 'Lopez','844576898');
insert into students(fname,lname,cin) values('Nancy','Carrigan', '811456779');
insert into students(fname,lname,cin) values('Joanas','Versaci','832545599');

insert into students(fname,lname,cin) values('Tommy', 'Bricker','544576899');
insert into students(fname,lname,cin) values('Marc','Kee', '812556788');
insert into students(fname,lname,cin) values('Coral','Bennet','852545598');
insert into students(fname,lname,cin) values('Carl', 'Waterbury','824575898');
insert into students(fname,lname,cin) values('Irwin','Magard', '811456758');
insert into students(fname,lname,cin) values('Earnest','Alarcon','832545595');
insert into students(fname,lname,cin) values('Alucard', 'Johnson','844576855');
insert into students(fname,lname,cin) values('Kevin','Spacey', '811456744');
insert into students(fname,lname,cin) values('Robert','Plant','832545533');
insert into students(fname,lname,cin) values('Kevin', 'Ferderline','844576800');

insert into students(fname,lname,cin) values('Michael', 'Hsu','844576899');
insert into students(fname,lname,cin) values('Shyla','Gambino', '812456788');
insert into students(fname,lname,cin) values('Arturo','Ferrero','822545598');
insert into students(fname,lname,cin) values('Robbin', 'Stinson','824576898');
insert into students(fname,lname,cin) values('Malcolm','Middle', '817454788');
insert into students(fname,lname,cin) values('Reese','Witherspoon','832545598');
insert into students(fname,lname,cin) values('Jerry', 'Seinfeild','847576898');
insert into students(fname,lname,cin) values('Seto','Kaiba', '811454788');
insert into students(fname,lname,cin) values('Yugi','Moto','832745598');
insert into students(fname,lname,cin) values('Joey', 'Wheeler','840576898');

insert into students(fname,lname,cin) values('Mike', 'Hanson','644576899');
insert into students(fname,lname,cin) values('Khloe','Gutierrez', '612456788');
insert into students(fname,lname,cin) values('Chris','Pine','622545598');
insert into students(fname,lname,cin) values('Richard', 'Stella','624576898');
insert into students(fname,lname,cin) values('Ricky','Rubio', '617454788');
insert into students(fname,lname,cin) values('Jimmy','Kimmel','632545598');
insert into students(fname,lname,cin) values('Alex', 'Trebek','647576898');
insert into students(fname,lname,cin) values('Katy','Perry', '611454788');
insert into students(fname,lname,cin) values('Vanessa','Blaze','632745598');
insert into students(fname,lname,cin) values('Cain', 'Song','640576898');

insert into students(fname,lname,cin) values('Meghan','Markle','744576899');
insert into students(fname,lname,cin) values('Jeff','Hornacek', '712456788');
insert into students(fname,lname,cin) values('Kirk','Cousins','722545598');
insert into students(fname,lname,cin) values('Steve', 'Kerr','724576898');
insert into students(fname,lname,cin) values('Tom','Brady','717454788');
insert into students(fname,lname,cin) values('Bill','Hader','732545598');
insert into students(fname,lname,cin) values('James','Harden','747576898');
insert into students(fname,lname,cin) values('Karen','Scott', '711454788');
insert into students(fname,lname,cin) values('Ashley','Graham','732745598');
insert into students(fname,lname,cin) values('Victor','Oladipo','740576898');

insert into students(fname,lname,cin) values('Paul','George','844576897');
insert into students(fname,lname,cin) values('Kawhi','Leonard', '812456787');
insert into students(fname,lname,cin) values('Pau','Gasol','822545597');
insert into students(fname,lname,cin) values('Tony','Parker','824576897');
insert into students(fname,lname,cin) values('Danny','Green', '817454787');
insert into students(fname,lname,cin) values('Manu','Ginobali','832545597');
insert into students(fname,lname,cin) values('Patty','Mills','847576897');
insert into students(fname,lname,cin) values('Jordan','Bell', '811454787');
insert into students(fname,lname,cin) values('Becca','Kufrin','832745597');
insert into students(fname,lname,cin) values('Ellen', 'Page','840576897');