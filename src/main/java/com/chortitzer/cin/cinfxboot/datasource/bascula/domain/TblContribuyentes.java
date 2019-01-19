/*
 * Copyright 2018 - Cooperativa CHortitzer Ltda.
 */
package com.chortitzer.cin.cinfxboot.datasource.bascula.domain;

import javax.persistence.*;

@Entity
@Table(name = "tbl_contribuyentes")
public class TblContribuyentes {
    private static final long serialVersionUID = 2612267594783505620L;

    @Column(name = "razon_social", nullable = false, length = 256)
    protected String razonSocial;

    @Column(name = "dv", nullable = false, length = 1)
    protected String dv;

    @Id
    @Column(name = "ruc_sin_dv", nullable = false, length = 20)
    protected String rucSinDv;

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setDv(String dv) {
        this.dv = dv;
    }

    public String getDv() {
        return dv;
    }

    public void setRucSinDv(String rucSinDv) {
        this.rucSinDv = rucSinDv;
    }

    public String getRucSinDv() {
        return rucSinDv;
    }

}
