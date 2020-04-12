<?php

namespace RefugiesBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Vich\UploaderBundle\Mapping\Annotation as Vich;
use Vich\UploaderBundle\Form\Type\VichFileType;

/**
 * Refugies
 * @Vich\Uploadable
 * @ORM\Table(name="refugies")
 * @ORM\Entity(repositoryClass="RefugiesBundle\Repository\RefugiesRepository")
 */
class Refugies
{
    /**
     * @var integer
     *
     * @ORM\Column(name="idref", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idref;


    /**
     * @return int
     */
    public function getIdref()
    {
        return $this->idref;
    }



    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=30, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom", type="string", length=30, nullable=false)
     */
    private $prenom;

    /**
     * @var integer
     *
     * @ORM\Column(name="age", type="integer", nullable=false)
     */
    private $age;

    /**
     * @var string
     *
     * @ORM\Column(name="pays", type="string", length=30, nullable=false)
     */
    private $pays;

    /**
     * @Vich\UploadableField(mapping="user_photo",fileNameProperty="image")
     * @ORM\Column(name="image", type="string", length=255, nullable=false)
     *
     * @var File
     */
    private $image;


    /**
     * @ORM\ManyToOne(targetEntity="CampsBundle\Entity\Camps")
     *
     * @ORM\JoinColumn(name="camps_id",referencedColumnName="idCamp")
     */
    private $camps;






    /**
     * Set nom
     *
     * @param string $nom
     *
     * @return Refugies
     */
    public function setNom($nom)
    {
        $this->nom = $nom;

        return $this;
    }

    /**
     * Get nom
     *
     * @return string
     */
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * Set prenom
     *
     * @param string $prenom
     *
     * @return Refugies
     */
    public function setPrenom($prenom)
    {
        $this->prenom = $prenom;

        return $this;
    }

    /**
     * Get prenom
     *
     * @return string
     */
    public function getPrenom()
    {
        return $this->prenom;
    }

    /**
     * Set age
     *
     * @param integer $age
     *
     * @return Refugies
     */
    public function setAge($age)
    {
        $this->age = $age;

        return $this;
    }

    /**
     * Get age
     *
     * @return integer
     */
    public function getAge()
    {
        return $this->age;
    }

    /**
     * Set pays
     *
     * @param string $pays
     *
     * @return Refugies
     */
    public function setPays($pays)
    {
        $this->pays = $pays;

        return $this;
    }

    /**
     * Get pays
     *
     * @return string
     */
    public function getPays()
    {
        return $this->pays;
    }

    /**
     * Set image
     *
     * @param string $image
     *
     * @return Refugies
     */
    public function setImage($image)
    {
        $this->image = $image;

        return $this;
    }

    /**
     * Get image
     *
     * @return string
     */
    public function getImage()
    {
        return $this->image;
    }



    /**
     * Set camps
     *
     * @param \CampsBundle\Entity\Camps $camps
     *
     * @return Refugies
     */
    public function setCamps(\CampsBundle\Entity\Camps $camps = null)
    {
        $this->camps = $camps;

        return $this;
    }

    /**
     * Get camps
     *
     * @return \CampsBundle\Entity\Camps
     */
    public function getCamps()
    {
        return $this->camps;
    }
}
