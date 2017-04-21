/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Алена
 */
@Entity
@Table(name = "vis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vis_1.findAll", query = "SELECT v FROM Vis_1 v"),
    @NamedQuery(name = "Vis_1.findById", query = "SELECT v FROM Vis_1 v WHERE v.id = :id"),
    @NamedQuery(name = "Vis_1.findByName", query = "SELECT v FROM Vis_1 v WHERE v.name = :name"),
    @NamedQuery(name = "Vis_1.findByComment", query = "SELECT v FROM Vis_1 v WHERE v.comment = :comment")})
public class Vis_1 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "comment")
    private String comment;

    public Vis_1() {
    }

    public Vis_1(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vis_1)) {
            return false;
        }
        Vis_1 other = (Vis_1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "view.Vis_1[ id=" + id + " ]";
    }
    
}
