<?php

namespace ProduitBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\HttpFoundation\File\File;

use Vich\UploaderBundle\Mapping\Annotation as Vich;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * Produits
 *
 * @ORM\Table(name="produits", uniqueConstraints={@ORM\UniqueConstraint(name="nomProd", columns={"nomProd"})})
 * @ORM\Entity
 * @ORM\Entity(repositoryClass="ProduitBundle\Repository\ProduitsRepository")
 */
class Produits
{
    /**
     * @var integer
     *
     * @ORM\Column(name="idProd", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idprod;

    /**
     * @var string
     *
     * @ORM\Column(name="nomProd", type="string", length=30, nullable=false)
     */
    private $nomprod;

    /**
     *
     * @ORM\ManyToOne(targetEntity="Categorie")
     * @ORM\JoinColumn(name="categorie_id", referencedColumnName="id"   )
     */
    private $categorie_id;
    /**
     * @var string
     *
     * @ORM\Column(name="nomRef", type="string", length=30, nullable=false)
     */
    private $nomref;


    /**
     * @var string
     *
     * @ORM\Column(name="img", type="string", length=200, nullable=false)
     */
    private $img;

    /**
     * @var integer
     *
     * @ORM\Column(name="prix", type="integer", nullable=false)
     */
    private $prix;

    /**
     * @var string
     *
     * @ORM\Column(name="dispo", type="string", length=30, nullable=true)
     */
    private $dispo;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=200, nullable=false)
     */
    private $description;

    /**
     * @var integer
     *
     * @ORM\Column(name="likes", type="integer", nullable=true)
     */
    private $likes;

    /**
     * @var integer
     *
     * @ORM\Column(name="views", type="integer", nullable=true)
     */
    private $views;

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
     * @return mixed
     */
    public function getCategorieId()
    {
        return $this->categorie_id;
    }

    /**
     * @param mixed $categorie_id
     */
    public function setCategorieId($categorie_id)
    {
        $this->categorie_id = $categorie_id;
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
    public function getDispo()
    {
        return $this->dispo;
    }

    /**
     * @param string $dispo
     */
    public function setDispo($dispo)
    {
        $this->dispo = $dispo;
    }

    /**
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * @param string $description
     */
    public function setDescription($description)
    {
        $this->description = $description;
    }

    /**
     * @return int
     */
    public function getLikes()
    {
        return $this->likes;
    }

    /**
     * @param int $likes
     */
    public function setLikes($likes)
    {
        $this->likes = $likes;
    }

    /**
     * @return int
     */
    public function getViews()
    {
        return $this->views;
    }

    /**
     * @param int $views
     */
    public function setViews($views)
    {
        $this->views = $views;
    }



}

