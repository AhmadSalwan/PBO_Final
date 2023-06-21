#MyConfig
Didalam myconfig,terdapat method untuk menghubungkan program ini dengan database SQL.Disini terdapat 4 method CRUD
Create (Menambahkan Data)
Read(Membaca Data)
Update(Memperbarui Data)
D(Menghapus Data)
Selain itu,class ini berisi method connection() sebagai jembatan yang menghubungkan data input dengan database SQL

#Layout
Didalam file ini,terdapat layout template untuk menjalankan aplikasi 

#Controller
Didalam file controller,terdapat perulangan untuk memanggil method-method di class MyConfig.Contriller juga bisa memanggil method runs() pada class Layout.Perulangan di class ini akan berjalan terus menerus,hingga user menekan inputan 5 yang menghentikan program

#App
Dalam class ini,akan dipanggil method Controller run() untuk menjalankan semua program