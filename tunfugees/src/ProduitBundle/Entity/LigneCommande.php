<?php

namespace ProduitBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * LigneCommande
 *
 * @ORM\Table(name="ligne_commande", uniqueConstraints={@ORM\UniqueConstraint(name="idProd", columns={"idProd"})})
 * @ORM\Entity
 * @ORM\Entity(repositoryClass="ProduitBundle\Repository\LigneCommandeRepository")
 */
class LigneCommande
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    public $id;

    /**
     * @var integer
     *
     * @ORM\Column(name="id_commande", type="integer", nullable=false)
     */
    public $idCommande;

    /**
     * @var integer
     *
     * @ORM\Column(name="idProd", type="integer", nullable=false,unique=true)
     */
    public $idprod;

    /**
     * @var integer
     *
     * @ORM\Column(name="id_utilisateur", type="integer", nullable=false)
     */
    public $idUtilisateur;

    /**
     * @var float
     *
     * @ORM\Column(name="prixProd", type="float", precision=10, scale=0, nullable=false)
     */
    public $prixprod;

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
    public function getIdCommande()
    {
        return $this->idCommande;
    }

    /**
     * @param int $idCommande
     */
    public function setIdCommande($idCommande)
    {
        $this->idCommande = $idCommande;
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
     * @return int
     */
    public function getIdUtilisateur()
    {
        return $this->idUtilisateur;
    }

    /**
     * @param int $idUtilisateur
     */
    public function setIdUtilisateur($idUtilisateur)
    {
        $this->idUtilisateur = $idUtilisateur;
    }

    /**
     * @return float
     */
    public function getPrixprod()
    {
        return $this->prixprod;
    }

    /**
     * @param float $prixprod
     */
    public function setPrixprod($prixprod)
    {
        $this->prixprod = $prixprod;
    }


}

