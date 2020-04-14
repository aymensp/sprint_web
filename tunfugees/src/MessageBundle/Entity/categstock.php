<?php

namespace MessageBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * categstock
 *
 * @ORM\Table(name="categstock")
 * @ORM\Entity(repositoryClass="MessageBundle\Repository\categstockRepository")
 */
class categstock
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;



    /**
     * @var string
     *
     * @ORM\Column(name="nomcateg", type="string", length=255)
     */
    private $nomcateg;




    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }



    /**
     * Set nomcateg
     *
     * @param string $nomcateg
     *
     * @return nomcateg
     */
    public function setnomcateg($nomcateg)
    {
        $this->nomcateg = $nomcateg;

        return $this;
    }

    /**
     * Get nomcateg
     *
     * @return string
     */
    public function getnomcateg()
    {
        return $this->nomcateg;
    }





}

