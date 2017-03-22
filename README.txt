README
Nume: Gogea
Prenume : Mihail
Titlu proiect : Programare orientate pe obiecte ( MyCloud )

Observatii :
Proiectul a fost lucrat in windows 7 cu ajutorul programului Eclipse (Mars) , fiind ultima versiune a lui Eclipse.
Observatiile din Observer Pattern , adica logarile , delogarile si exceptiile vor fi salvate in fisierul evidenta.txt in care va fi salvat la iesirea din program( la apelarea comenzii : exit).

In folderul : fisiere comenzi , am introdus doua fisiere : comenzi1 si comenzi2 realizate de mine pentru a se vedea o testare a programului , model luat dupa fisierul .txt din cadrul arhivei temei.
Modul de implementare :

Sistemul de fisiere :
Am construit interfata Command pentru a implementa comenziile necesare.
Pentru a returna clasa corespunzatoare comenzii dorite in functie de comanda scria ,
am facut o clasa commandFactory unde se returneaza un obiect de tipul Command.
Am creat interfata Repository, iar apoi am creat o clasa abstract AbsRepository care implementeaza aceasta interfata , iar apoi am creat clasele Directory si File care extend aceasta clasa abstracta . 
Clasa abstracta am facuto pentru a scrie metodele commune ale celor 2 clase.
Am construit clasa Permisiune pentru a retine userul si permisiunile r sau w sau rw.
Am construit clasa abstracta systemCommand pentru comenziile echo , login, logout,userinfo . ( Parola root : “rootpas”)
Clasele abstract writeCommand si readCommand le-am facut pentru comenziile de citire si afisare.
Am facut o clasa User in care retin datele necesare a unui user.
Clasa user respecta tipul de Singleton Pattern , am facut putin diferit ,dar respecta deoarece aceasta clasa instantiata doar odata in clasa principala terminal si se instantiaza astfel
userCurent = useriManagement.getUser(“guest”); unde in clasa useriManagement am un vector de useri adica toti userii din sistem.
Clasele si interfetele urmatoare : Logger, Observer, Subject au fost create pentru Observer Pattern , in care se salveaza logarile , delogarile si exceptiile in fisierul evident.txt
Toate exceptiile , dar si login si logout extind interfata Observer pentru a putea fi introduse in Logger , asa cum sugereaza Observer Pattern.
Pentru fiecare comanda de scriere sau citire am mai scri detalii si in comentarile din clasele respective , de asemenea codul poate fi usor de inteles , straduinduma sa am un coding style cat mai placut.
Pentru fiecare File cand este creat trebuie introdus inneaparat numele dorit, dimensiunea (am presupus in biti) . Permisiunea fiind data daca se doreste. Tipul fisierului va fi dat random si umplut cu biti 0 si 1 daca este binar sau cu text daca este de tip text.
Pentru mkdir trebuie introdus astfel : numele folderului si permisiunea dorita.
Sunt acceptate urmatoarele pentru comanda mkdir :
mkdir folder permisiune
mkdir folder
mkdir foder1 folder2 folder3 folder4 ..
Daca nu au fost introduse permisiuni atunci nimeni nu va avea drept de citire si scriere asupra lor, acest fapt este valabil si pentru comanda touch.
Pentru comanda ls sunt acceptate urmatoarele :
ls
ls –r
ls –a
ls –ar
ls folder
ls fisier
Pentru comanda cd sunt acceptate urmatoarele comenzi :
cd folder
cd cale-absoluta
Pentru comanda rm se poate sterge directoarele goale si fisierele. Daca se doreste stergerea directoarelor care au dimensiune atunci se va apela rm –r folder. Comanda rm merge si pentru cale absoluta.
Comanda echo afiseaza tot ce se scrie dupa ea , dar daca continutul care se doreste a fi afisat este cuprins intre ghilimele atunci se va afisa fara ghilimele , deoarece asa este in linux terminal.
Comenzile urmatoare afiseaza out-tul formatat in ferestre interne:
ls –l –POO
userinfo –POO
echo –POO
Am implementat si clasele comenzilor upload si sync cu tot cu exceptii.
Pentru a se iesi din program se tasteaza comanda : “exit”.

Am respectat in acest proiect Design-ul Pattern .

Sistemul Cloud :
Am creat o clasa StoreStation care sa fie statie pentru a uploada . Am facut o clasa clonare pentru a clona un director. 
Comenzile pentru upload si sync se vor da din directorul curent unde se doreste a se face upload unui director si ineaparat se face sync din acel director. Nu trebuie sa se stearga directorul a carui i s-a facut upload , se poate sterge sau modifica doar in interiorul lui , deoarece sync se va da din directorul parinte a acestuia , exemplu:
In directorul folder1 : upload folder2
// acum se poate sterge sau crea orice in interiorul directorului folder2
Tot in directorul folder1: sync folder2
//se va revenii in folder 2 la dimensiunea si continutul vechi cand a fost salvat in cloud.
Intefata grafica task 3:
In clasa main se va apela programul.
In clasa terminal am facut interfata grafica , derivand din JFrame. Am introdus Look-And-Feel Nimbus pentru o prezentare mai 
frumoasa a interfetei.

Terminalul meu este compus dintr-un JTextArea unde sunt introduse comenzile date si outputul si un JTextField unde introduc comanda.
Comenzile formatate trebuie introduse astfel : “ls –l –POO”, “userinfo –POO” si “echo –POO”.
Pentru comenzile ls -l -POO , userinfo -POO si echo –POO vor aparea ca intr-un desktop ( cu ajutorul clase JDesktop) noi ferestre unde vor aparea outputul in mod formatat.

In plus am introdus look-and-feel Nimbus, deoarece asa cum am explicat si mai sus Seaglas care era continut in arhiva temei nu afisa frumos terminalul si ferestrele interne.
Am introdus desemenea si arhiva.jar din continutul arhivei temei , iar pentru a seta acest look and feel , trebuie definita variabila care am introduso deja astfel(metoda 1) :
Window->Preferences->Java->Build Path->Classpath Variables
Si se va selecta Seaglas_Jar.
Iar daca se doreste introducerea look-and-feel seaglas din cadrul arhivei(metoda 2) se va proceda urmator:
- Se apasa Build si apoi build path, in dreapta se va alege Add external jar si se cauta arhiva dorita (adica seaglas.jar) si se apasa ok.
- In locul try{ … UIManager…} se va introduce urmatoare bucata de cod - - try { - UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel"); - } catch (Exception e) { - e.printStackTrace(); - }
Comanda “clear” introdusa va curata ecranu .
Comanda “exit” se va inchide programul.
Am setat acestei aplicatii o iconita , ce se poate vedea in stanga sus cand se acceseaza programul sau jos in bara de aplicatii.
