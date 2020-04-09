<?php

namespace EventBundle\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\ORM\Mapping as ORM;
/*use Symfony\Component\Validator\Constraints as Assert;
use Gedmo\Mapping\Annotation as Gedmo;*/

use Symfony\Component\HttpFoundation\File\File;
use Vich\UploaderBundle\Mapping\Annotation as Vich;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Event
 * @ORM\Table(name="event")
 * @ORM\Entity(repositoryClass="EventBundle\Repository\EventRepository")
 */
class Event
{
    /**
     * Event constructor.
     * @param int $idEvent
     * @param string $nomevent
     * @param string $adresse
     * @param \DateTime $date
     * @param string $description
     * @param int $nbrmax
     * @param string $image
     * @param string $volontaires
     */



    /**
     * @return int
     */
    public function getIdEvent()
    {
        return $this->idEvent;
    }

    /**
     * @param int $idEvent
     */
    public function setIdEvent($idEvent)
    {
        $this->idEvent = $idEvent;
    }

    /**
     * @return string
     */
    public function getNomevent()
    {
        return $this->nomevent;
    }

    /**
     * @param string $nomevent
     */
    public function setNomevent($nomevent)
    {
        $this->nomevent = $nomevent;
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
     * @return \DateTime
     */
    public function getDate()
    {
        return $this->date;
    }

    /**
     * @param \DateTime $date
     */
    public function setDate($date)
    {
        $this->date = $date;
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
    public function getImage()
    {
        return $this->image;
    }

    /**
     * @param string $image
     */
    public function setImage($image)
    {
        $this->image = $image;
    }
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
     * @ORM\Column(name="nomEvent", type="string", length=30, nullable=false)
     */
    private $nomevent;

    /**
     * @var string
     *
     * @ORM\Column(name="adresse", type="string", length=30, nullable=false)
     */
    private $adresse;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date", nullable=false)
     * @Assert\GreaterThan("today")
     */
    private $date;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="text", length=65535, nullable=false)
     */
    private $description;

    /**
     * @var integer
     *
     * @ORM\Column(name="nbrMax", type="integer", nullable=false)
     */
    private $nbrmax;

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
     * @var string
     *
     * @ORM\Column(name="image", type="string", length=255, nullable=true)
     */
    private $image;


    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\ManyToMany(targetEntity="Volontaire")
     * @ORM\JoinTable(name="event_Volontaire",
     *   joinColumns={
     *     @ORM\JoinColumn(name="evenement_id", referencedColumnName="id", onDelete="CASCADE")
     *   },
     *   inverseJoinColumns={
     *     @ORM\JoinColumn(name="volontaire_id", referencedColumnName="id_vol")
     *   }
     * )
     */
    private $volontaires;

    public function __construct()
    {
        $this->volontaires = new ArrayCollection();
    }

    public function getVolontaires()
    {
        return $this->volontaires;
    }

    /**
     * Add profile
     * @param \EventBundle\Entity\Volontaire $p
     */
    public function addVolontaires(Volontaire $p)
    {
        $this->volontaires[] = $p;

        return $this;
    }

    /**
     * Remove profile
     *
     * @param \EventBundle\Entity\Volontaire $p
     */
    public function removeVolontaires(Volontaire $p)
    {
        $this->volontaires->removeElement($p);
    }







}


