function toggleVisibility() {
    const jenisLembaga = document.querySelector(
        'input[name="jenisLembaga"]:checked'
    );
    if (!jenisLembaga) return;

    const negaraSection = document.getElementById("negaraSection");
    const provinsiSection = document.getElementById("provinsiSection");
    const kotaSection = document.getElementById("kotaSection");
    const kecamatanSection = document.getElementById("kecamatanSection");

    // Reset semua bagian
    negaraSection.classList.add("hidden");
    provinsiSection.classList.add("hidden");
    kotaSection.classList.add("hidden");
    kecamatanSection.classList.add("hidden");

    if (jenisLembaga.value === "Internasional") {
        negaraSection.classList.remove("hidden");
    } else if (jenisLembaga.value === "Nasional") {
        provinsiSection.classList.remove("hidden");
        kotaSection.classList.remove("hidden");
        kecamatanSection.classList.remove("hidden");
    } else {
        // lokal
        kecamatanSection.classList.remove("hidden"); // Tampilkan kecamatan saja
    }
}

// Initialize visibility
document.addEventListener("DOMContentLoaded", () => {
    const jenisLembagaRadios = document.querySelectorAll('input[name="jenisLembaga"]');

    jenisLembagaRadios.forEach(radio => {
        radio.addEventListener("click", toggleVisibility);
    });
    toggleVisibility(); // Set initial visibility based on selected radio button
});

function getSubdistrictByRegencyId(url) {
    fetch(url)
        .then(response => response.json())
        .then(data => {
            if(Array.isArray(data.data)) {
                var subdistrictElement = document.getElementById('subdistricts');
                subdistrictElement.innerHTML = '';
                subdistrictElement.innerHTML = '<option value="0">-Pilih Kecamatan-</option>'
                data.data.forEach(function (subdistrict) {
                    var option = document.createElement('option')
                    option.value = subdistrict.id;
                    option.text = subdistrict.name;
                    subdistrictElement.appendChild(option);
                });
            } else {
                console.log("Data from API is not an array")
            }
        })
        .catch((error) => {
            console.log('Error:', error)
        });
}

document.getElementById('jenisLokal').addEventListener('click', ()=> {
    var url = 'http://localhost:8080/api/regencies/3509/subdistricts';
    getSubdistrictByRegencyId(url);
});

document.getElementById('jenisNasional').addEventListener('click', ()=> {
    var regencyElement = document.getElementById('regencies');
    var regencyId = regencyElement.value;
    var url = 'http://localhost:8080/api/regencies/'+regencyId+'/subdistricts';
    getSubdistrictByRegencyId(url);
});

document.getElementById('provinces').addEventListener('change', function() {
    // Ambil nilai yang dipilih dari dropdown

    var provinceId = this.value;

    // Buat URL dengan parameter query string
    var url = 'http://localhost:8080/api/provinces/'+provinceId+'/regencies';

    // Kirim request GET ke server
    fetch(url)
        .then(response => response.json())
        .then(data => {
            // Jika data berada di dalam properti 'data'
            if (Array.isArray(data.data)) {
                var regencyElement = document.getElementById('regencies');
                var subdistrictElement = document.getElementById("subdistricts");
                regencyElement.innerHTML = '';
                subdistrictElement.innerHTML = '';
                regencyElement.innerHTML = '<option value="0">-Pilih Kabupaten-</option>';
                subdistrictElement.innerHTML = '<option value="0">-Pilih Kecamatan-</option>'
                // Proses data yang berada di dalam properti 'data'
                data.data.forEach(function(regency) {
                    var option = document.createElement('option');
                    option.value = regency.id;
                    option.text = regency.name;
                    regencyElement.appendChild(option);
                });
            } else {
                console.error('Data from API is not an array');
            }
        })
        .catch((error) => {
            console.error('Error:', error);
        });
});

document.getElementById("regencies").addEventListener('change', function (){
    var regencyId = this.value;
    var url = 'http://localhost:8080/api/regencies/'+regencyId+'/subdistricts';
    getSubdistrictByRegencyId(url);
});


