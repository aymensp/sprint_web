<?php

namespace ProduitBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * PanierProduit
 *
 * @ORM\Table(name="panier_produit", uniqueConstraints={@ORM\UniqueConstraint(name="idProd", columns={"idProd"})})
 * @ORM\Entity
 * @ORM\Entity(repositoryClass="ProduitBundle\Repository\PanierProduitRepository")
 */
class PanierProduit
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
     * @var integer
     *
     * @ORM\Column(name="idProd", type="integer", nullable=false)
     */
    private $idprod;

    /**
     * @var string
     *
     * @ORM\Column(name="nomProd", type="string", length=30, nullable=false)
     */
    private $nomprod;

    /**
     * @var string
     *
     * @ORM\Column(name="nomRef", type="string", length=30, nullable=false)
     */
    private $nomref;

    /**
     * @var integer
     *
     * @ORM\Column(name="prix", type="integer", nullable=false)
     */
    private $prix;

    /**
     * @var string
     *
     * @ORM\Column(name="img", type="string", length=255, nullable=false)
     */
    private $img;

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
     * @return int
     */
    public function getIdprod()
    {
        return $this->idprod;
    }

    /**
     * @param int $idprod
     */
    public function setIdprod($idprod)
    {
        $this->idprod = $idprod;
    }

    /**
     * @return string
     */
    public function getNomprod()
    {
        return $this->nomprod;
    }

    /**
     * @param string $nomprod
     */
    public function setNomprod($nomprod)
    {
        $this->nomprod = $nomprod;
    }

    /**
     * @return string
     */
    public function getNomref()
    {
        return $this->nomref;
    }

    /**
     * @param string $nomref
     */
    public function setNomref($nomref)
    {
        $this->nomref = $nomref;
    }

    /**
     * @return int
     */
    public function getPrix()
    {
        return $this->prix;
    }

    /**
     * @param int $prix
     */
    public function setPrix($prix)
    {
        $this->prix = $prix;
    }

    /**
     * @return string
     */
    public function getImg()
    {
        return $this->img;
    }

    /**
     * @param string $img
     */
    public function setImg($img)
    {
        $this->img = $img;
    }


}

