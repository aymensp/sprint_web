<?php

namespace UserBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Annonce
 *
 * @ORM\Table(name="annonce")
 * @ORM\Entity
 */
class Annonce
{
    /**
     * @var integer
     *
     * @ORM\Column(name="idannonce", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idannonce;

    /**
     * @var string
     *
     * @ORM\Column(name="adresse", type="string", length=30, nullable=false)
     */
    private $adresse;

    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=30, nullable=false)
     */
    private $type;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="text", length=65535, nullable=false)
     */
    private $description;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="datePub", type="datetime", nullable=false)
     */
    private $datepub;


}

