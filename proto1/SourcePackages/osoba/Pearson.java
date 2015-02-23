/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package osoba;


public class Pearson {
    
    private String imie;
    private String nazwisko;
    private String login;
    private String haslo;
    private String mail;
    private String status;
    private int numerPokoju;

    public Pearson() {
        numerPokoju=1;
    }

    public Pearson(String imie, String nazwisko, String login, String haslo, String mail, String status, int numerPokoju) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.login = login;
        this.haslo = haslo;
        this.mail = mail;
        this.status = status;
        this.numerPokoju = numerPokoju;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumerPokoju() {
        return numerPokoju;
    }

    public void setNumerPokoju(int numerPokoju) {
        this.numerPokoju = 1;
    }
}
