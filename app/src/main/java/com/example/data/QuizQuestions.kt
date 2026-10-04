package com.example.data

import com.example.model.CodeLanguage
import com.example.model.Question
import com.example.model.QuizOption
import com.example.model.Topic

object QuizQuestions {
    val questions: List<Question> = listOf(
        // ==========================================
        // SOALAN 1: XML & findViewById() [Topic: XML_UI]
        // Correct: D
        // ==========================================
        Question(
            id = 1,
            topic = Topic.XML_UI,
            questionText = "Apakah kod Java yang betul untuk menghubungkan pembolehubah btnSubmit dengan elemen Button dalam fail XML?",
            codeSnippet = """// activity_main.xml
<Button
    android:id="@+id/btnSubmit"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Hantar" />

// MainActivity.java
Button btnSubmit;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    [ KOD ? ]
}""",
            language = CodeLanguage.JAVA,
            options = listOf(
                QuizOption("A", "btnSubmit = getViewById(R.id.btnSubmit);"),
                QuizOption("B", "btnSubmit = View.findViewById(R.layout.btnSubmit);"),
                QuizOption("C", "btnSubmit = new Button(R.id.btnSubmit);"),
                QuizOption("D", "btnSubmit = findViewById(R.id.btnSubmit);")
            ),
            correctOptionIndex = 3, // D
            explanationCorrect = "Tepat sekali! Kaedah findViewById(R.id.btnSubmit) digunakan dalam Activity untuk memautkan rujukan objek Java dengan view daripada fail layout XML berdasarkan ID.",
            explanationIncorrect = "Jawapan belum tepat. Kaedah rasmi Android SDK untuk merujuk komponen UI daripada XML berdasarkan ID ialah findViewById(R.id.nama_id). Tiada kaedah getViewById() atau sintaks pembina new Button(id) untuk memautkan view sedia ada."
        ),

        // ==========================================
        // SOALAN 2: UI Input & getText().toString() [Topic: XML_UI]
        // Correct: C
        // ==========================================
        Question(
            id = 2,
            topic = Topic.XML_UI,
            questionText = "Apakah kod yang betul untuk mengambil teks yang ditaip oleh pengguna daripada EditText dan menyimpannya ke dalam pembolehubah String?",
            codeSnippet = """EditText edtName = findViewById(R.id.edtName);
Button btnDisplay = findViewById(R.id.btnDisplay);
TextView tvOutput = findViewById(R.id.tvOutput);

btnDisplay.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        [ KOD ? ]
        tvOutput.setText("Selamat Datang, " + inputName);
    }
});""",
            language = CodeLanguage.JAVA,
            options = listOf(
                QuizOption("A", "String inputName = edtName.getText();"),
                QuizOption("B", "String inputName = edtName.toString();"),
                QuizOption("C", "String inputName = edtName.getText().toString();"),
                QuizOption("D", "String inputName = edtName.getValue();")
            ),
            correctOptionIndex = 2, // C
            explanationCorrect = "Cemerlang! Kaedah edtName.getText() mengembalikan objek jenis Editable. Kita wajib memanggil .toString() untuk menukarkannya kepada jenis data Java String.",
            explanationIncorrect = "Kurang tepat. edtName.getText() sahaja menghasilkan jenis Editable (bukan String). Memanggil edtName.toString() pula akan menukar objek widget EditText itu sendiri kepada string, bukan teks kandungannya."
        ),

        // ==========================================
        // SOALAN 3: XML android:id [Topic: XML_UI]
        // Correct: A
        // ==========================================
        Question(
            id = 3,
            topic = Topic.XML_UI,
            questionText = "Apakah atribut XML yang tepat untuk memberikan ID unik edtMatric supaya komponen ini boleh diakses dalam fail Java?",
            codeSnippet = """<!-- res/layout/activity_main.xml -->
<EditText
    [ KOD ? ]
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Masukkan No. Pendaftaran"
    android:inputType="text" />""",
            language = CodeLanguage.XML,
            options = listOf(
                QuizOption("A", "android:id=\"@+id/edtMatric\""),
                QuizOption("B", "android:name=\"@id/edtMatric\""),
                QuizOption("C", "android:identifier=\"edtMatric\""),
                QuizOption("D", "android:tag=\"@+id/edtMatric\"")
            ),
            correctOptionIndex = 0, // A
            explanationCorrect = "Tahniah! Simbol @+id/ mengarahkan sistem binaan Android untuk mencipta ID baharu dengan nama edtMatric dalam kelas sumber R.id.",
            explanationIncorrect = "Jawapan salah. Sintaks rasmi Android XML untuk menetapkan pengenal pasti unik ialah android:id=\"@+id/nama_id\". Simbol tambah (+) penting bagi mendaftar sumber ID baharu dalam R.java."
        ),

        // ==========================================
        // SOALAN 4: Toast Syntax [Topic: TOAST]
        // Correct: B
        // ==========================================
        Question(
            id = 4,
            topic = Topic.TOAST,
            questionText = "Antara berikut, pernyataan manakah yang merupakan sintaks Java yang lengkap dan betul bagi memaparkan pesanan Toast ringkas?",
            codeSnippet = """btnSave.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        [ KOD ? ]
    }
});""",
            language = CodeLanguage.JAVA,
            options = listOf(
                QuizOption("A", "Toast.show(MainActivity.this, \"Data berjaya disimpan\", Toast.LENGTH_SHORT);"),
                QuizOption("B", "Toast.makeText(MainActivity.this, \"Data berjaya disimpan\", Toast.LENGTH_SHORT).show();"),
                QuizOption("C", "Toast.makeText(this, \"Data berjaya disimpan\", Toast.SHORT).display();"),
                QuizOption("D", "new Toast(\"Data berjaya disimpan\", Toast.LENGTH_SHORT).show();")
            ),
            correctOptionIndex = 1, // B
            explanationCorrect = "Tepat! Sintaks piawai Toast ialah Toast.makeText(Context, CharSequence, int duration).show(). Tiga argumen ini bersama panggilan kaedah .show() adalah wajib.",
            explanationIncorrect = "Kurang tepat. Kelas Toast tidak mempunyai kaedah statik Toast.show() dan tiada pembina new Toast(String, int). Kita mesti menggunakan kaedah kilang Toast.makeText(...) diikuti dengan .show()."
        ),

        // ==========================================
        // SOALAN 5: Toast Debugging (.show() missing) [Topic: TOAST]
        // Correct: D
        // ==========================================
        Question(
            id = 5,
            topic = Topic.TOAST,
            questionText = "Pelajar mendapati tiada sebarang pesanan Toast dipaparkan pada skrin telefon apabila butang ditekan. Mengapakah situasi ini berlaku?",
            codeSnippet = """// Kod di dalam onClick butang:
Toast.makeText(MainActivity.this, "Ralat: Sila isi maklumat!", Toast.LENGTH_LONG);""",
            language = CodeLanguage.JAVA,
            options = listOf(
                QuizOption("A", "Nilai tempoh masa sepatutnya Toast.LENGTH_SHORT sahaja."),
                QuizOption("B", "Parameter pertama mestilah getApplication() dan bukan MainActivity.this."),
                QuizOption("C", "Teks mesej tidak dibenarkan mengandungi tanda seru (!)."),
                QuizOption("D", "Kaedah .show() tidak dipanggil pada penghujung pernyataan Toast.")
            ),
            correctOptionIndex = 3, // D
            explanationCorrect = "Benar sekali! Toast.makeText() sekadar membina dan mengkonfigurasi objek Toast dalam memori. Tanpa memanggil .show(), notifikasi tersebut tidak akan pernah dipaparkan pada skrin.",
            explanationIncorrect = "Salah. Tempoh LENGTH_LONG adalah sah, tanda seru tidak menyebabkan masalah, dan MainActivity.this adalah Context yang betul. Puncanya ialah ketiadaan panggilan kaedah .show()."
        ),

        // ==========================================
        // SOALAN 6: Toast Duration Constant [Topic: TOAST]
        // Correct: C
        // ==========================================
        Question(
            id = 6,
            topic = Topic.TOAST,
            questionText = "Apakah pemalar (constant) yang betul dalam kelas Toast untuk memaparkan pesanan dalam tempoh yang lebih lama (kira-kira 3.5 saat)?",
            codeSnippet = """Toast.makeText(
    MainActivity.this, 
    "Selamat Menduduki Kuiz!", 
    [ KOD ? ]
).show();""",
            language = CodeLanguage.JAVA,
            options = listOf(
                QuizOption("A", "Toast.TIME_LONG"),
                QuizOption("B", "Toast.DURATION_LONG"),
                QuizOption("C", "Toast.LENGTH_LONG"),
                QuizOption("D", "Toast.DELAY_LONG")
            ),
            correctOptionIndex = 2, // C
            explanationCorrect = "Betul! Android Toast menyediakan dua pemalar masa rasmi: Toast.LENGTH_SHORT (sekitar 2 saat) dan Toast.LENGTH_LONG (sekitar 3.5 saat).",
            explanationIncorrect = "Kurang tepat. Kelas Toast tidak mempunyai pemalar TIME_LONG, DURATION_LONG atau DELAY_LONG. Hanya Toast.LENGTH_SHORT dan Toast.LENGTH_LONG yang diiktiraf."
        ),

        // ==========================================
        // SOALAN 7: Explicit Intent Creation [Topic: EXPLICIT_INTENT]
        // Correct: B
        // ==========================================
        Question(
            id = 7,
            topic = Topic.EXPLICIT_INTENT,
            questionText = "Apakah kod yang betul untuk membina Explicit Intent bagi berpindah dari MainActivity ke SecondActivity?",
            codeSnippet = """btnPindah.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        [ KOD ? ]
        startActivity(intent);
    }
});""",
            language = CodeLanguage.JAVA,
            options = listOf(
                QuizOption("A", "Intent intent = new Intent(SecondActivity.class, MainActivity.this);"),
                QuizOption("B", "Intent intent = new Intent(MainActivity.this, SecondActivity.class);"),
                QuizOption("C", "Intent intent = new Intent(Intent.ACTION_VIEW, SecondActivity.class);"),
                QuizOption("D", "Intent intent = Intent.startActivity(MainActivity.this, SecondActivity.class);")
            ),
            correctOptionIndex = 1, // B
            explanationCorrect = "Tepat! Pembina Explicit Intent menerima dua parameter mengikut susunan: (1) Konteks Activity semasa iaitu MainActivity.this, dan (2) Kelas Activity destinasi iaitu SecondActivity.class.",
            explanationIncorrect = "Belum tepat. Susunan parameter pembina Intent mestilah (Context packageContext, Class<?> cls). Pilihan A terbalik susunannya, manakala Pilihan C mencampurkan tindakan Implicit dengan class sasaran."
        ),

        // ==========================================
        // SOALAN 8: startActivity() [Topic: EXPLICIT_INTENT]
        // Correct: D
        // ==========================================
        Question(
            id = 8,
            topic = Topic.EXPLICIT_INTENT,
            questionText = "Apakah kaedah yang perlu dipanggil untuk melancarkan atau memulakan Activity baharu berdasarkan objek Intent yang telah dibina?",
            codeSnippet = """Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
[ KOD ? ]""",
            language = CodeLanguage.JAVA,
            options = listOf(
                QuizOption("A", "launchActivity(intent);"),
                QuizOption("B", "intent.start();"),
                QuizOption("C", "openActivity(intent);"),
                QuizOption("D", "startActivity(intent);")
            ),
            correctOptionIndex = 3, // D
            explanationCorrect = "Tepat sekali! Kaedah startActivity(intent) ialah kaedah terbina dalam kelas Activity Android yang bertanggungjawab menghantar permohonan ke sistem OS untuk melancarkan Activity baharu.",
            explanationIncorrect = "Kurang tepat. Kaedah standard Android SDK untuk memulakan Activity ialah startActivity(intent). Kaedah seperti launchActivity, openActivity atau intent.start() tidak wujud dalam Android Activity."
        ),

        // ==========================================
        // SOALAN 9: Explicit Intent Concept [Topic: EXPLICIT_INTENT]
        // Correct: A
        // ==========================================
        Question(
            id = 9,
            topic = Topic.EXPLICIT_INTENT,
            questionText = "Mengapakah kod berikut dikategorikan sebagai \"Explicit Intent\"?",
            codeSnippet = """// Dalam MainActivity.java
Intent intent = new Intent(MainActivity.this, DetailActivity.class);
startActivity(intent);""",
            language = CodeLanguage.JAVA,
            options = listOf(
                QuizOption("A", "Kerana nama kelas komponen sasaran (DetailActivity.class) dinyatakan secara nyata dan khusus.", false),
                QuizOption("B", "Kerana ia menggunakan kebenaran khas (permission) di dalam fail AndroidManifest.xml.", false),
                QuizOption("C", "Kerana ia bergantung pada sistem operasi Android untuk memilih aplikasi yang sesuai.", false),
                QuizOption("D", "Kerana ia beroperasi secara automatik tanpa memerlukan interaksi pengguna.", false)
            ),
            correctOptionIndex = 0, // A
            explanationCorrect = "Bijak! Explicit Intent dipanggil \"explicit\" (nyata) kerana pembangun menyatakan dengan tepat komponen sasaran yang hendak dibuka (DetailActivity.class) tanpa membiarkan sistem membuat pilihan.",
            explanationIncorrect = "Jawapan salah. Ciri utama Explicit Intent ialah penentuan komponen sasaran secara spesifik (nama kelas Activity). Menyerahkan pilihan aplikasi kepada OS pula adalah sifat Implicit Intent."
        ),

        // ==========================================
        // SOALAN 10: Back Navigation Intent Context [Topic: EXPLICIT_INTENT]
        // Correct: C
        // ==========================================
        Question(
            id = 10,
            topic = Topic.EXPLICIT_INTENT,
            questionText = "Di dalam SecondActivity, apakah parameter yang paling tepat untuk menggantikan [ KOD ? ] bagi navigasi kembali ke MainActivity?",
            codeSnippet = """// Di dalam SecondActivity.java
btnKembali.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent([ KOD ? ]);
        startActivity(intent);
    }
});""",
            language = CodeLanguage.JAVA,
            options = listOf(
                QuizOption("A", "MainActivity.class, SecondActivity.this"),
                QuizOption("B", "MainActivity.this, SecondActivity.class"),
                QuizOption("C", "SecondActivity.this, MainActivity.class"),
                QuizOption("D", "getApplicationContext(), \"MainActivity\"")
            ),
            correctOptionIndex = 2, // C
            explanationCorrect = "Hebat! Semasa berada di dalam SecondActivity, konteks semasa ialah SecondActivity.this dan Activity tujuan ialah MainActivity.class.",
            explanationIncorrect = "Belum tepat. Memandangkan kod ini dieksekusi di dalam SecondActivity, sumber semasa ialah SecondActivity.this, manakala destinasinya ialah MainActivity.class."
        ),

        // ==========================================
        // SOALAN 11: putExtra() Data Transfer [Topic: DATA_TRANSFER]
        // Correct: D
        // ==========================================
        Question(
            id = 11,
            topic = Topic.DATA_TRANSFER,
            questionText = "Apakah kaedah yang betul untuk melampirkan teks nama pengguna dengan kunci (key) \"KEY_NAME\" ke dalam objek Intent?",
            codeSnippet = """String studentName = edtName.getText().toString();
Intent intent = new Intent(MainActivity.this, ResultActivity.class);

[ KOD ? ]

startActivity(intent);""",
            language = CodeLanguage.JAVA,
            options = listOf(
                QuizOption("A", "intent.attachData(\"KEY_NAME\", studentName);"),
                QuizOption("B", "intent.sendExtra(studentName, \"KEY_NAME\");"),
                QuizOption("C", "intent.addBundle(\"KEY_NAME\", studentName);"),
                QuizOption("D", "intent.putExtra(\"KEY_NAME\", studentName);")
            ),
            correctOptionIndex = 3, // D
            explanationCorrect = "Tepat sekali! Kaedah intent.putExtra(String name, String value) digunakan untuk menyelitkan data tambahan berasaskan pasangan kunci-nilai (key-value pair) ke dalam Intent.",
            explanationIncorrect = "Kurang tepat. Kaedah rasmi untuk memasukkan data ke dalam Intent ialah putExtra(). Kaedah attachData() atau sendExtra() tidak wujud dalam kelas Intent."
        ),

        // ==========================================
        // SOALAN 12: getStringExtra() [Topic: DATA_TRANSFER]
        // Correct: B
        // ==========================================
        Question(
            id = 12,
            topic = Topic.DATA_TRANSFER,
            questionText = "Apakah kod yang betul di dalam ResultActivity untuk menerima data String yang dihantar melalui Intent dengan kunci \"KEY_NAME\"?",
            codeSnippet = """// Di dalam ResultActivity.java (onCreate)
TextView tvResult = findViewById(R.id.tvResult);

Intent intent = getIntent();
[ KOD ? ]

tvResult.setText("Nama Pelajar: " + receivedName);""",
            language = CodeLanguage.JAVA,
            options = listOf(
                QuizOption("A", "String receivedName = intent.getText(\"KEY_NAME\");"),
                QuizOption("B", "String receivedName = intent.getStringExtra(\"KEY_NAME\");"),
                QuizOption("C", "String receivedName = intent.getExtra(\"KEY_NAME\").toString();"),
                QuizOption("D", "String receivedName = intent.findExtra(\"KEY_NAME\");")
            ),
            correctOptionIndex = 1, // B
            explanationCorrect = "Cemerlang! Kaedah intent.getStringExtra(\"KEY_NAME\") digunakan khusus untuk mengambil data jenis String yang telah dipautkan dalam Intent.",
            explanationIncorrect = "Salah. Kelas Intent menyediakan kaedah pemerolehan jenis khusus seperti getStringExtra(), getIntExtra(), getBooleanExtra(). Kaedah getText() atau findExtra() bukan kaedah Intent bagi mengekstrak nilai String."
        ),

        // ==========================================
        // SOALAN 13: Key Mismatch Debugging [Topic: DATA_TRANSFER]
        // Correct: A
        // ==========================================
        Question(
            id = 13,
            topic = Topic.DATA_TRANSFER,
            questionText = "Mengapakah pembolehubah studentName dalam SecondActivity mengembalikan nilai null?",
            codeSnippet = """// Dalam MainActivity.java:
String name = edtName.getText().toString();
Intent intent = new Intent(MainActivity.this, SecondActivity.class);
intent.putExtra("studentName", name);
startActivity(intent);

// Dalam SecondActivity.java:
String studentName = getIntent().getStringExtra("name");""",
            language = CodeLanguage.JAVA,
            options = listOf(
                QuizOption("A", "Kunci (key) dalam putExtra (\"studentName\") tidak sepadan dengan kunci dalam getStringExtra (\"name\").", false),
                QuizOption("B", "Data perlu ditukar kepada format JSON sebelum boleh dihantar melalui Intent.", false),
                QuizOption("C", "Kaedah getIntent() hanya sah dipanggil di luar kaedah onCreate().", false),
                QuizOption("D", "Kaedah putExtra() tidak menyokong penghantaran pembolehubah String.", false)
            ),
            correctOptionIndex = 0, // A
            explanationCorrect = "Tepat sekali! Nilai kunci (key) dalam Intent adalah case-sensitive dan mestilah sama persis. Data dihantar dengan key \"studentName\" tetapi SecondActivity cuba mencari key \"name\", menyebabkan hasil carian bernilai null.",
            explanationIncorrect = "Salah. Nilai null terhasil semata-mata kerana ketidakpadanan kunci: penghantar menggunakan \"studentName\" manakala penerima mencari \"name\". putExtra menyokong String secara langsung tanpa JSON."
        ),

        // ==========================================
        // SOALAN 14: Implicit Intent - ACTION_VIEW Web [Topic: IMPLICIT_INTENT]
        // Correct: C
        // ==========================================
        Question(
            id = 14,
            topic = Topic.IMPLICIT_INTENT,
            questionText = "Apakah kod yang betul untuk melengkapkan Implicit Intent bagi membuka portal web Politeknik menggunakan pelayar web peranti?",
            codeSnippet = """btnWeb.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(
            Intent.ACTION_VIEW,
            [ KOD ? ]
        );
        startActivity(intent);
    }
});""",
            language = CodeLanguage.JAVA,
            options = listOf(
                QuizOption("A", "Uri.fromPath(\"https://www.polytechnic.edu.my\")"),
                QuizOption("B", "new URL(\"https://www.polytechnic.edu.my\")"),
                QuizOption("C", "Uri.parse(\"https://www.polytechnic.edu.my\")"),
                QuizOption("D", "Uri.toUri(\"https://www.polytechnic.edu.my\")")
            ),
            correctOptionIndex = 2, // C
            explanationCorrect = "Tahniah! Kaedah Uri.parse(String uriString) digunakan untuk menukar rentetan URL kepada objek Uri yang difahami oleh sistem Android bagi Intent.ACTION_VIEW.",
            explanationIncorrect = "Kurang tepat. Uri.parse(...) adalah kaedah statik yang tepat dalam pakej android.net.Uri untuk menukar teks alamat web kepada objek Uri."
        ),

        // ==========================================
        // SOALAN 15: Implicit Intent - ACTION_DIAL Phone [Topic: IMPLICIT_INTENT]
        // Correct: B
        // ==========================================
        Question(
            id = 15,
            topic = Topic.IMPLICIT_INTENT,
            questionText = "Apakah kod yang betul untuk membuka aplikasi pendail telefon (phone dialer) dengan nombor 0123456789 siap terpapar pada keypad?",
            codeSnippet = """btnCall.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        [ KOD ? ]
        startActivity(intent);
    }
});""",
            language = CodeLanguage.JAVA,
            options = listOf(
                QuizOption("A", "Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(\"tel:0123456789\"));"),
                QuizOption("B", "Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(\"tel:0123456789\"));"),
                QuizOption("C", "Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(\"phone:0123456789\"));"),
                QuizOption("D", "Intent intent = new Intent(Intent.DIALER, Uri.parse(\"tel:0123456789\"));")
            ),
            correctOptionIndex = 1, // B
            explanationCorrect = "Cemerlang! Intent.ACTION_DIAL bersama skema Uri \"tel:\" membuka skrin pendail tanpa terus membuat panggilan, dan tidak memerlukan kebenaran sensitif CALL_PHONE.",
            explanationIncorrect = "Salah. ACTION_CALL akan terus memanggil nombor telefon secara automatik (dan memerlukan izin khas di AndroidManifest). Untuk sekadar membuka pendail dengan nombor tertera, ACTION_DIAL wajib digunakan."
        ),

        // ==========================================
        // SOALAN 16: Explicit vs Implicit Distinction [Topic: IMPLICIT_INTENT]
        // Correct: D
        // ==========================================
        Question(
            id = 16,
            topic = Topic.IMPLICIT_INTENT,
            questionText = "Apakah perbezaan utama antara intent1 dan intent2 dalam coretan kod di bawah?",
            codeSnippet = """// Contoh Intent 1:
Intent intent1 = new Intent(MainActivity.this, SecondActivity.class);

// Contoh Intent 2:
Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:3.1390,101.6869"));""",
            language = CodeLanguage.JAVA,
            options = listOf(
                QuizOption("A", "intent1 adalah Implicit Intent, manakala intent2 adalah Explicit Intent.", false),
                QuizOption("B", "intent1 hanya boleh dijalankan di emulator, manakala intent2 hanya di peranti fizikal.", false),
                QuizOption("C", "intent1 memerlukan sambungan internet aktif, manakala intent2 tidak memerlukannya.", false),
                QuizOption("D", "intent1 ialah Explicit Intent (sasaran kelas Activity spesifik), manakala intent2 ialah Implicit Intent (tindakan umum untuk diselesaikan oleh aplikasi luar).", false)
            ),
            correctOptionIndex = 3, // D
            explanationCorrect = "Tepat sekali! intent1 mengkhususkan SecondActivity.class secara terang (Explicit), manakala intent2 mengarahkan sistem mencari aplikasi mana-mana yang boleh memaparkan koordinat geo (Implicit).",
            explanationIncorrect = "Kurang tepat. intent1 ialah Explicit Intent kerana kelas Activity sasaran dinyatakan secara tepat. intent2 ialah Implicit Intent kerana ia menyatakan aksi (ACTION_VIEW) dan data (Uri geo:)."
        ),

        // ==========================================
        // SOALAN 17: ACTION_VIEW Action Name [Topic: IMPLICIT_INTENT]
        // Correct: A
        // ==========================================
        Question(
            id = 17,
            topic = Topic.IMPLICIT_INTENT,
            questionText = "Apakah pemalar tindakan (Action) yang tepat untuk memaparkan carian lokasi peta tersebut pada aplikasi peta peranti?",
            codeSnippet = """Intent intent = new Intent(
    [ KOD ? ],
    Uri.parse("geo:0,0?q=Politeknik+Ungku+Omar")
);
startActivity(intent);""",
            language = CodeLanguage.JAVA,
            options = listOf(
                QuizOption("A", "Intent.ACTION_VIEW"),
                QuizOption("B", "Intent.ACTION_LOCATION"),
                QuizOption("C", "Intent.ACTION_NAVIGATE"),
                QuizOption("D", "Intent.ACTION_MAP")
            ),
            correctOptionIndex = 0, // A
            explanationCorrect = "Betul! Intent.ACTION_VIEW ialah tindakan sejagat dalam Android untuk memaparkan sebarang data kepada pengguna mengikut skema Uri yang dibekalkan (seperti http, https, geo, atau tel).",
            explanationIncorrect = "Kurang tepat. Pemalar seperti ACTION_LOCATION atau ACTION_NAVIGATE tidak wujud dalam Intent teras Android. Pemalar yang betul ialah Intent.ACTION_VIEW."
        ),

        // ==========================================
        // SOALAN 18: Challenge - XML ID Mismatch NullPointerException [Topic: DEBUGGING]
        // Correct: C
        // ==========================================
        Question(
            id = 18,
            topic = Topic.DEBUGGING,
            questionText = "Aplikasi mengalami \"App Crash\" akibat NullPointerException sebaik sahaja butang ditekan. Berdasarkan kod di bawah, apakah punca sebenar ralat tersebut?",
            codeSnippet = """// activity_main.xml
<EditText
    android:id="@+id/etUser"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
<Button
    android:id="@+id/btnKira"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Kira" />

// MainActivity.java
EditText etUser;
Button btnKira;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    etUser = findViewById(R.id.etUsername); // Baris 1
    btnKira = findViewById(R.id.btnKira);   // Baris 2

    btnKira.setOnClickListener(v -> {
        String u = etUser.getText().toString(); // Baris 3
        Toast.makeText(this, u, Toast.LENGTH_SHORT).show();
    });
}""",
            language = CodeLanguage.JAVA,
            options = listOf(
                QuizOption("A", "Baris 2: Butang btnKira tidak boleh menggunakan ungkapan lambda v ->.", false),
                QuizOption("B", "Baris 3: Teks dari etUser tidak boleh ditukar kepada toString().", false),
                QuizOption("C", "Baris 1: R.id.etUsername tidak wujud dalam XML kerana ID sebenar ialah android:id=\"@+id/etUser\", menyebabkan pembolehubah etUser bernilai null.", false),
                QuizOption("D", "Toast tidak dibenarkan memaparkan teks daripada pembolehubah String.", false)
            ),
            correctOptionIndex = 2, // C
            explanationCorrect = "Analisis yang sangat tajam! Dalam XML, id ialah 'etUser', tetapi dalam Java pelajar mencari 'R.id.etUsername'. Akibatnya findViewById mengembalikan null. Apabila etUser.getText() dipanggil, berlakulah NullPointerException!",
            explanationIncorrect = "Salah analisis. Puncanya berlaku pada Baris 1: ID dalam XML ialah etUser, manakala kod Java memanggil R.id.etUsername. Ini menyebabkan etUser menjadi null dan mencetuskan NullPointerException semasa etUser.getText() dijalankan."
        ),

        // ==========================================
        // SOALAN 19: Challenge - Missing startActivity() [Topic: DEBUGGING]
        // Correct: A
        // ==========================================
        Question(
            id = 19,
            topic = Topic.DEBUGGING,
            questionText = "Pengguna menekan butang tetapi SecondActivity langsung tidak dibuka dan tiada sebarang perpindahan skrin berlaku. Apakah kod penting yang tertinggal dalam FirstActivity?",
            codeSnippet = """// FirstActivity.java
btnSubmit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
        intent.putExtra("SKOR_MARKAH", 95);
        
        [ KOD YANG TERTINGGAL ]
    }
});""",
            language = CodeLanguage.JAVA,
            options = listOf(
                QuizOption("A", "startActivity(intent);"),
                QuizOption("B", "intent.send();"),
                QuizOption("C", "intent.openActivity();"),
                QuizOption("D", "getIntent().launch(intent);")
            ),
            correctOptionIndex = 0, // A
            explanationCorrect = "Tepat sekali! Membina objek Intent dan mengisi putExtra() sahaja hanyalah persediaan data. Panggilan startActivity(intent) adalah mandatori untuk benar-benar memberitahu sistem Android agar membuka skrin baharu.",
            explanationIncorrect = "Kurang tepat. Menghasilkan objek Intent sahaja tidak akan membuka skrin secara automatik. Arahan startActivity(intent); mesti dipanggil untuk memulakan Activity sasaran."
        ),

        // ==========================================
        // SOALAN 20: Challenge - URL Scheme Protocol Missing [Topic: DEBUGGING]
        // Correct: D
        // ==========================================
        Question(
            id = 20,
            topic = Topic.DEBUGGING,
            questionText = "Apabila butang ditekan, aplikasi mengalami ralat \"ActivityNotFoundException: No Activity found to handle Intent\". Bagaimanakah cara yang betul untuk membetulkan isu tersebut?",
            codeSnippet = """btnLaman.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String url = "www.politeknik.edu.my"; 
        Intent intent = new Intent(
            Intent.ACTION_VIEW, 
            Uri.parse(url)
        );
        startActivity(intent);
    }
});""",
            language = CodeLanguage.JAVA,
            options = listOf(
                QuizOption("A", "Tukar Intent.ACTION_VIEW kepada Intent.ACTION_WEB."),
                QuizOption("B", "Gantikan Uri.parse(url) kepada Uri.fromFile(url)."),
                QuizOption("C", "Padamkan baris startActivity(intent) dan gantikan dengan Toast."),
                QuizOption("D", "Sertakan skema protokol yang lengkap seperti \"https://www.politeknik.edu.my\" dalam pembolehubah url.")
            ),
            correctOptionIndex = 3, // D
            explanationCorrect = "Hebat dan tepat! Sistem Android memerlukan skema protokol yang sah (seperti \"https://\" atau \"http://\") untuk mengenal pasti bahawa Intent ini adalah untuk pelayar web. Rentetan tanpa skema tidak dapat dipadankan dengan aplikasi web!",
            explanationIncorrect = "Kurang tepat. Intent filter pelayar web memerlukan skema protokol yang sah (seperti https:// atau http://). Rentetan \"www.politeknik.edu.my\" tanpa skema menyebabkan Android gagal mencari sebarang Activity yang boleh mengendalikannya."
        )
    )
}
