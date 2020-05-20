<?php

namespace CampsBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Camps
 *
 * @ORM\Table(name="camps")
 * @ORM\Entity
 */
class Camps
{
    /**
     * @var integer
     *
     * @ORM\Column(name="idCamp", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idcamp;

    /**
     * @var integer
     *
     * @ORM\Column(name="nbrmax", type="integer", nullable=false)
     */
    private $nbrmax;

    /**
     * @var string
     *
     * @ORM\Column(name="nomCamp", type="string", length=30, nullable=false)
     */
    private $Categories;

    /**
     * @var string
     *
     * @ORM\Column(name="adresse", type="string", length=30, nullable=false)
     */
    private $adresse;

    /**
     * @return int
     */
    public function getIdcamp()
    {
        return $this->idcamp;
    }

    /**
     * @param int $idcamp
     */
    public function setIdcamp($idcamp)
    {
        $this->idcamp = $idcamp;
    }

    /**
     * @return int
     */
    public function getNbrmax()
    {
        return $this->nbrmax;
    }

    /**
     * @param int $nbrmax
     */
    public function setNbrmax($nbrmax)
    {
        $this->nbrmax = $nbrmax;
    }



    /**
     * @return string
     */
    public function getAdresse()
    {
        return $this->adresse;
    }

    /**
     * @param string $adresse
     */
    public function setAdresse($adresse)
    {
        $this->adresse = $adresse;
    }

    /**
     * @return string
     */
    public function getCategories()
    {
        return $this->Categories;
    }

    /**
     * @param string $Categories
     */
    public function setCategories($Categories)
    {
        $this->Categories = $Categories;
    }




}

