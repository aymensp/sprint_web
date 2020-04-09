<?php

namespace ProduitBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Commande
 *
 * @ORM\Table(name="commande")
 * @ORM\Entity
 * @ORM\Entity(repositoryClass="ProduitBundle\Repository\CommandeRepository")
 */
class Commande
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
     * @ORM\Column(name="etat_commande", type="string", length=11, nullable=false)
     */
    private $etatCommande;

    /**
     * @var string
     *
     * @ORM\Column(name="date_emission", type="string", length=30, nullable=false)
     */
    private $dateEmission;

    /**
     * @var string
     *
     * @ORM\Column(name="id_utilisateur", type="string", length=30, nullable=false)
     */
    private $idUtilisateur;

    /**
     * @var integer
     *
     * @ORM\Column(name="prixTotal", type="integer", nullable=false)
     */
    private $prixtotal;

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
    public function getEtatCommande()
    {
        return $this->etatCommande;
    }

    /**
     * @param string $etatCommande
     */
    public function setEtatCommande($etatCommande)
    {
        $this->etatCommande = $etatCommande;
    }

    /**
     * @return string
     */
    public function getDateEmission()
    {
        return $this->dateEmission;
    }

    /**
     * @param string $dateEmission
     */
    public function setDateEmission($dateEmission)
    {
        $this->dateEmission = $dateEmission;
    }

    /**
     * @return string
     */
    public function getIdUtilisateur()
    {
        return $this->idUtilisateur;
    }

    /**
     * @param string $idUtilisateur
     */
    public function setIdUtilisateur($idUtilisateur)
    {
        $this->idUtilisateur = $idUtilisateur;
    }

    /**
     * @return int
     */
    public function getPrixtotal()
    {
        return $this->prixtotal;
    }

    /**
     * @param int $prixtotal
     */
    public function setPrixtotal($prixtotal)
    {
        $this->prixtotal = $prixtotal;
    }


}

