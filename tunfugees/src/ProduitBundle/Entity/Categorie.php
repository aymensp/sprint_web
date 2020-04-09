<?php

namespace ProduitBundle\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\ORM\Mapping as ORM;
use EcoBundle\Entity\Annonce;

/**
 * Categorie
 *
 * @ORM\Table(name="categorie")
 * @ORM\Entity
 * @ORM\Entity(repositoryClass="ProduitBundle\Repository\CategorieRepository")
 */
class Categorie
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="libelle", type="string", length=200, nullable=false)
     */
    private $libelle;



    public function __construct() {

        $this->produits = new ArrayCollection();
    }

    public function addPrdouit(Produits $produit)
    {
        $this->produits[] = $produit;

        return $this;
    }
    public function removePrdouit(Produits $produit)
    {
        $this->produits->removeElement($produit);
    }

    /**
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param int $id
     */
    public function setId($id)
    {
        $this->id = $id;
    }

    /**
     * @return string
     */
    public function getLibelle()
    {
        return $this->libelle;
    }

    /**
     * @param string $libelle
     */
    public function setLibelle($libelle)
    {
        $this->libelle = $libelle;
    }


}

