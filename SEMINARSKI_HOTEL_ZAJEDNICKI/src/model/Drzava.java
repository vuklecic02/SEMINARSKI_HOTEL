/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package model;

/**
 *
 * @author vuk
 */
public enum Drzava {
Afganistan,

Albanija,

Alžir,

Andora,

Angola,

Antigva_i_Barbuda,

Argentina,

Armenija,

Australija,

Austrija,

Azerbejdžan,

Bahami,

Bahrein,

Bangladeš,

Barbados,

Belorusija,

Belgija,

Beliz,

Benin,

Butan,

Bolivija,

Bosna_i_Hercegovina,

Bocvana,

Brazil,

Brunej,

Bugarska,

Burkina_Faso,

Burundi,

Kambodža,

Kamerun,

Kanada,

Zelenortska_Ostrva,

Centralnoafrička_Republika,

Čad,

Čile,

Kina,

Kolumbija,

Komori,

Demokratska_Republika_Kongo,

Republika_Kongo,

Kostarika,

Hrvatska,

Kuba,

Kipar,

Češka,

Danska,

Džibuti,

Dominika,

Dominikanska_Republika,

Ekvador,

Egipat,

El_Salvador,

Ekvatorijalna_Gvineja,

Eritreja,

Estonija,

Eswatini,

Etiopija,

Fidži,

Finska,

Francuska,

Gabon,

Gambija,

Gruzija,

Nemačka,

Gana,

Grčka,

Grenada,

Gvatemala,

Gvineja,

Gvineja_Bisao,

Haiti,

Honduras,

Mađarska,

Island,

Indija,

Indonezija,

Iran,

Irak,

Irska,

Izrael,

Italija,

Obala_Slonovače,

Jamajka,

Japan,

Jordan,

Kazahstan,

Kenija,

Kiribati,

Kuvajt,

Kirgistan,

Laos,

Letonija,

Liban,

Lesoto,

Liberija,

Libija,

Lihtenštajn,

Litvanija,

Luksemburg,

Madagaskar,

Malavi,

Malezija,

Maldivi,

Mali,

Malta,

Maršalska_Ostrva,

Mauritanija,

Mauricijus,

Meksiko,

Mikronezija,

Moldavija,

Monako,

Mongolija,

Crna_Gora,

Maroko,

Mozambik,

Mjanmar,

Namibija,

Nauru,

Nepal,

Holandija,

Novi_Zeland,

Nikaragva,

Niger,

Nigerija,

Severna_Koreja,

Severna_Makedonija,

Norveška,

Oman,

Pakistan,

Palau,

Panama,

Papua_Nova_Gvineja,

Paragvaj,

Peru,

Filipini,

Poljska,

Portugal,

Katar,

Rumunija,

Rusija,

Ruanda,

Sveti_Kitts_i_Nevis,

Sveti_Lucia,

Sveti_Vincent_i_Grenadini,

Samoa,

San_Marino,

Sao_Tome_i_Principe,

Saudijska_Arabija,

Senegal,

Srbija,

Sejšeli,

Sijera_Leone,

Singapur,

Slovačka,

Slovenija,

Solomonska_Ostrva,

Somalija,

Južna_Afrika,

Južna_Koreja,

Južni_Sudan,

Španija,

Šri_Lanka,

Sudan,

Surinam,

Švedska,

Švajcarska,

Sirija,

Tadžikistan,

Tanzanija,

Tajland,

Togo,

Tonga,

Trinidad_i_Tobago,

Tunis,

Turska,

Turkmenistan,

Tuvalu,

Uganda,

Ukrajina,

Ujedinjeni_Arapski_Emirati,

Ujedinjeno_Kraljevstvo,

Sjedinjene_Američke_Države,

Urugvaj,

Uzbekistan,

Vanuatu,

Venecuela,

Vijetnam,

Jemen,

Zambija,

Zimbabve,;

    @Override
    public String toString() {
        return name().replace("_", " ");
    }
    
    public static Drzava izBazeString(String dbVrednost) {
        return Drzava.valueOf(dbVrednost.replace(" ", "_"));
    }
}

